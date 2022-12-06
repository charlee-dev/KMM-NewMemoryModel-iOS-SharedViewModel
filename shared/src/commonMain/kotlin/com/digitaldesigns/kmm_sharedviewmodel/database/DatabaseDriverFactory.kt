package com.digitaldesigns.kmm_sharedviewmodel.database

import com.squareup.sqldelight.db.SqlDriver

expect class DriverFactory {
    fun createDriver(): SqlDriver
}
