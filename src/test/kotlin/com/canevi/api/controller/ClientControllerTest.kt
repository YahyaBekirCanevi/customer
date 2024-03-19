package com.canevi.api.controller

import com.canevi.api.domain.request.SaveClient
import com.canevi.data.model.Client
import com.canevi.data.repository.ClientCrudRepository
import io.micronaut.http.HttpStatus
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.`when`
import java.util.*

@MicronautTest
class ClientControllerTest {

    @Inject
    lateinit var clientCrudRepository: ClientCrudRepository

    lateinit var clientController: ClientController
    @BeforeEach
    fun setup() {
        clientController = ClientController(clientCrudRepository)
    }

    @Test
    fun `test find client by id`() {
        // Mock the behavior of clientRepository.findById("1")
        `when`(clientCrudRepository.findById("1")).thenReturn(Optional.of(Client("1", "Test", "123456")))

        val response = clientController.find("1")

        assertEquals("Test", response.get().name)
    }

    @Test
    fun `test save client`() {
        val saveClientRequest = SaveClient("New Client", "newpassword")
        val savedClient = Client("1", "New Client", "newpassword")

        // Mock the behavior of clientRepository.save(Client)
        `when`(clientCrudRepository.save(any(Client::class.java))).thenReturn(savedClient)

        val response = clientController.save(saveClientRequest)

        assertEquals(HttpStatus.CREATED, response.status)
        assertEquals("New Client", response.body().name)
    }

    @Test
    fun `test update client`() {
        val saveClientRequest = SaveClient("Updated Client", "updatedpassword")
        val updatedClient = Client("1", "Updated Client", "updatedpassword")

        // Mock the behavior of clientRepository.update("1", SaveClient)
        `when`(clientCrudRepository.update(any())).thenReturn(updatedClient)

        val response = clientController.update("1", saveClientRequest)

        assertEquals(HttpStatus.OK, response.status)
        assertEquals("1 client updated!", response.body())
    }
}
