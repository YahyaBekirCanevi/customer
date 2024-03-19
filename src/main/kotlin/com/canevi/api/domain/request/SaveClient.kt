package com.canevi.api.domain.request

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@Introspected
data class SaveClient(
    val name: String,
    val password: String
)