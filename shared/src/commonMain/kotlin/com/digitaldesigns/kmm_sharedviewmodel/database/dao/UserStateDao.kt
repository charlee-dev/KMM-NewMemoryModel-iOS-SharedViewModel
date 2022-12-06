package com.digitaldesigns.kmm_sharedviewmodel.database.dao

import com.digitaldesigns.kmm_sharedviewmodel.cache.KMMDatabase
import com.digitaldesigns.kmmsharedviewmodel.cache.UserState
import com.squareup.sqldelight.runtime.coroutines.asFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserStateDao(database: KMMDatabase) {
    private val userStateQueries = database.userStateQueries

    fun getUserState(): Flow<UserState?> {
        return userStateQueries.selectUserState()
            .asFlow()
            .map { it.executeAsOneOrNull() }
    }

    fun saveUserState(userId: String, token: String) {
        userStateQueries.transaction {
            userStateQueries.addUserState(userId, token)
        }
    }

    fun clearUserState() {
        println("clearUserState")
        userStateQueries.transaction {
            userStateQueries.deleteUserState()
        }
    }
}
