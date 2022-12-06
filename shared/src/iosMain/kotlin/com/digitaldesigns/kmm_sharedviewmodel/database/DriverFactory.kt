package com.digitaldesigns.kmm_sharedviewmodel.database

import com.digitaldesigns.kmm_sharedviewmodel.cache.KMMDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(KMMDatabase.Schema, "test.db")
    }
}
