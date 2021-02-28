package com.openclassrooms.magicgithub.api

import com.openclassrooms.magicgithub.model.User

interface ApiService {
    fun getUsers(): MutableList<User>
    fun generateRandomUser()
    fun deleteUser(user: User)
}
