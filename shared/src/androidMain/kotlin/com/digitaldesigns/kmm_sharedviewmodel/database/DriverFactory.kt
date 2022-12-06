package com.digitaldesigns.kmm_sharedviewmodel.database

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import com.digitaldesigns.kmm_sharedviewmodel.cache.KMMDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            KMMDatabase.Schema,
            context,
            "shoppe.db",
            callback = object : AndroidSqliteDriver.Callback(KMMDatabase.Schema) {
                override fun onConfigure(db: SupportSQLiteDatabase) {
                    super.onConfigure(db)
                    db.setForeignKeyConstraintsEnabled(true)
                }
            }
        )
    }
}
