package com.digitaldesigns.kmm_sharedviewmodel

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform