package com.digitaldesigns.kmm_sharedviewmodel.repository

import com.digitaldesigns.kmm_sharedviewmodel.database.Database
import com.digitaldesigns.kmm_sharedviewmodel.database.DriverFactory
import com.digitaldesigns.kmmsharedviewmodel.cache.UserState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

class AuthRepository(driverFactory: DriverFactory) {
    private val database = Database(driverFactory)

    suspend fun signIn(email: String, password: String): UserState? {
        delay(2000)
        val response = UserState(userId = email, token = email + password)
        database.userStateDao.saveUserState(response.userId, response.token)
        return response
    }

    suspend fun signUp(email: String, password: String): UserState? {
        delay(2000)
        val response = UserState(userId = email, token = password + email)
        saveUserState(response.userId, response.token)
        return response
    }

    fun checkUserState(): Flow<UserState?> {
        return database.userStateDao.getUserState()
    }

    fun signOut() {
        database.userStateDao.clearUserState()
    }

    private fun saveUserState(userId: String, token: String) {
        database.userStateDao.saveUserState(userId, token)
    }
}
