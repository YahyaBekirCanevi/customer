package com.canevi.api.controller

import com.canevi.api.domain.request.SaveClient
import com.canevi.data.model.Client
import com.canevi.data.repository.ClientCrudRepository
import io.micronaut.http.HttpStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever
import java.util.*

@ExtendWith(MockitoExtension::class)
class ClientControllerTest {

    @Mock
    lateinit var clientCrudRepository: ClientCrudRepository

    @InjectMocks
    lateinit var clientController: ClientController

    @Test
    fun `test find client by id`() {
        // Mock the behavior of clientRepository.findById("1")
        whenever(clientCrudRepository.findById("1")).thenReturn(Optional.of(Client("1", "Test", "123456")))

        val response = clientController.find("1")

        assertEquals("Test", response.get().name)
    }

    @Test
    fun `test save client`() {
        val saveClientRequest = SaveClient("New Client", "newpassword")
        val savedClient = Client("1", "New Client", "newpassword")

        // Mock the behavior of clientRepository.save(Client)
        whenever(clientCrudRepository.save(any(Client::class.java))).thenReturn(savedClient)

        val response = clientController.save(saveClientRequest)

        assertEquals(HttpStatus.CREATED, response.status)
        assertEquals("\'New Client\' client created!", response.body())
    }

    @Test
    fun `test update client`() {
        val saveClientRequest = SaveClient("Updated Client", "updatedpassword")
        val updatedClient = Client("1", "Updated Client", "updatedpassword")

        // Mock the behavior of clientRepository.update("1", SaveClient)
        whenever(clientCrudRepository.findById(any())).thenReturn(Optional.of(updatedClient))
        whenever(clientCrudRepository.update(any())).thenReturn(updatedClient)

        val response = clientController.update("1", saveClientRequest)

        assertEquals(HttpStatus.OK, response.status)
        assertEquals("1 client updated!", response.body())
    }
}
