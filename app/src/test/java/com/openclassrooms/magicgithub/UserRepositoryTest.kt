package com.openclassrooms.magicgithub

import com.openclassrooms.magicgithub.di.Injection.createUserRepository
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.openclassrooms.magicgithub.repository.UserRepository
import com.openclassrooms.magicgithub.api.FakeApiServiceGenerator
import org.junit.Before
import com.openclassrooms.magicgithub.model.User
import org.hamcrest.collection.IsIterableContainingInAnyOrder
import org.junit.Assert
import org.junit.Test
import java.util.stream.Collectors

/**
 * Unit test, which will execute on a JVM.
 * Testing UserRepository.
 */
@RunWith(JUnit4::class)
class UserRepositoryTest {
    private var userRepository: UserRepository? = null

    private val FAKE_USERS: List<User> = FakeApiServiceGenerator.FAKE_USERS
    private val FAKE_USERS_RANDOM = FakeApiServiceGenerator.FAKE_USERS_RANDOM
    @Before
    fun setup() {
        userRepository = createUserRepository()
    }

    // J'ai remplacé ce test par celui en dessous car je n'arrive pas à faire fonctionner le @get:Test et ej n'arrive pas à comprendre cette annotation...
    /*@get:Test
    val usersWithSuccess: Unit
        get() {
            val usersActual: List<User> = userRepository!!.users
            val usersExpected = FAKE_USERS
            Assert.assertThat(usersActual, IsIterableContainingInAnyOrder.containsInAnyOrder<Any>(*usersExpected.toTypedArray()))
        }
    */

    @Test
    fun usersWithSuccess() {
        val usersActual: List<User> = userRepository!!.users
        val usersExpected = FAKE_USERS
        Assert.assertThat(usersActual, IsIterableContainingInAnyOrder.containsInAnyOrder<Any>(*usersExpected.toTypedArray()))
    }

    @Test
    fun generateRandomUserWithSuccess() {
        userRepository!!.users.clear()
        userRepository!!.generateRandomUser()
        val (id, login, avatarUrl) = userRepository!!.users[0]
        Assert.assertEquals(1, userRepository!!.users.size.toLong())
        Assert.assertTrue(FAKE_USERS_RANDOM.stream().map(User::avatarUrl).collect(Collectors.toList()).contains(avatarUrl))
        Assert.assertTrue(FAKE_USERS_RANDOM.stream().map(User::id).collect(Collectors.toList()).contains(id))
        Assert.assertTrue(FAKE_USERS_RANDOM.stream().map(User::login).collect(Collectors.toList()).contains(login))
        Assert.assertTrue(FAKE_USERS.stream().map(User::avatarUrl).collect(Collectors.toList()).contains(avatarUrl))
        Assert.assertTrue(FAKE_USERS.stream().map(User::id).collect(Collectors.toList()).contains(id))
        Assert.assertTrue(FAKE_USERS.stream().map(User::login).collect(Collectors.toList()).contains(login))
    }

    @Test
    fun deleteUserWithSuccess() {
        val userToDelete = userRepository!!.users[0]
        userRepository!!.deleteUser(userToDelete)
        Assert.assertFalse(userRepository!!.users.contains(userToDelete))
    }
}