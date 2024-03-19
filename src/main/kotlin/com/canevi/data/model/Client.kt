package com.canevi.data.model

import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*

@Serdeable
@Entity
@Table(name = "client_data")
data class Client(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    val id: String? = null,
    @Column(name = "name", nullable = false, unique = true, length = 50)
    var name: String,
    @Column(name = "password", nullable = false, unique = true, length = 50)
    var password: String,
)