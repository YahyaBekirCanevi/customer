package com.canevi.api.controller

import com.canevi.api.domain.request.SaveClient
import com.canevi.data.model.Client
import com.canevi.data.repository.ClientCrudRepository
import com.oracle.svm.core.annotate.Inject
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import java.net.URI
import java.util.*
import kotlin.jvm.optionals.getOrElse

@ExecuteOn(TaskExecutors.BLOCKING)
@Controller("/client")
class ClientController(@Inject val clientRepository: ClientCrudRepository) {
    @Get("/{id}")
    fun find(id: String): Optional<Client> =
        clientRepository.findById(id)

    @Post
    fun save(@Body request: SaveClient): HttpResponse<Client> {
        val savedClient = clientRepository.save(Client(name = request.name, password = request.password))
        return HttpResponse.created(savedClient)
            .headers {
                it.location(location(savedClient.id!!))
            }
    }
    @Put("/{id}")
    fun update(id: String, @Body request: SaveClient): HttpResponse<String> {
        val updateClient = clientRepository.findById(id)
        val result = updateClient.getOrElse {
            return HttpResponse.badRequest()
        }
        result.name = request.name
        result.password = request.password
        val savedClient = clientRepository.update(result)
        return HttpResponse.ok("${savedClient.id} client updated!")
            .headers {
                it.location(location(savedClient.id!!))
            }
    }
    @Delete("/{id}")
    fun delete(id: String): HttpResponse<String> {
        clientRepository.deleteById(id)
        return HttpResponse.ok("$id client deleted!")
            .headers {
                it.location(location(id))
            }
    }

    private fun location(id: String): URI {
        return URI.create("/genres/$id")
    }
}