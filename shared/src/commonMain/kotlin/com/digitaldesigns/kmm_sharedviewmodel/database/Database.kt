package com.digitaldesigns.kmm_sharedviewmodel.database

import com.digitaldesigns.kmm_sharedviewmodel.cache.KMMDatabase
import com.digitaldesigns.kmm_sharedviewmodel.database.dao.UserStateDao

class Database(driverFactory: DriverFactory) {
    private val database = KMMDatabase(driverFactory.createDriver())

    val userStateDao = UserStateDao(database)
}
