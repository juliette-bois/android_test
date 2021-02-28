package com.openclassrooms.magicgithub.repository

import com.openclassrooms.magicgithub.api.ApiService
import com.openclassrooms.magicgithub.model.User

class UserRepository(private val apiService: ApiService) {

    //("A modifier")
    val users: MutableList<User>
        get() {
            return this.apiService.getUsers()
        }

    //("A modifier")
    fun generateRandomUser() {
        this.apiService.generateRandomUser()
    }

    //("A modifier")
    fun deleteUser(user: User?) {
        if (user != null) {
            this.apiService.deleteUser(user)
        }
    }
}
