package com.openclassrooms.magicgithub.ui.user_list

import android.os.Bundle
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import com.openclassrooms.magicgithub.databinding.ActivityListUserBinding
import com.openclassrooms.magicgithub.di.Injection
import com.openclassrooms.magicgithub.repository.UserRepository

class ListUserActivity : AppCompatActivity() {
    @VisibleForTesting
    lateinit var repository: UserRepository

    // Utiliser le View Binding
    private var _binding: ActivityListUserBinding? = null
    private val binding get() = _binding!!

    // OVERRIDE ---
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = Injection.createUserRepository()
        _binding = ActivityListUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}