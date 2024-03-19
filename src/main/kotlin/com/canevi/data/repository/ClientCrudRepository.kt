package com.canevi.data.repository

import com.canevi.data.model.Client
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository

@JdbcRepository(dialect = Dialect.POSTGRES)
interface ClientCrudRepository : CrudRepository<Client, String> {
}