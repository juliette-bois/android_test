package com.openclassrooms.magicgithub.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import com.openclassrooms.magicgithub.databinding.ListUserFragmentBinding
import com.openclassrooms.magicgithub.di.Injection
import com.openclassrooms.magicgithub.model.User
import com.openclassrooms.magicgithub.repository.UserRepository
import com.openclassrooms.magicgithub.ui.user_list.ListUserActivity
import com.openclassrooms.magicgithub.ui.user_list.UserListAdapter

class UserListFragment: Fragment(), UserListAdapter.Listener {
    // Add View Binding
    private var _binding: ListUserFragmentBinding? = null
    private val binding get() = _binding!!

    @VisibleForTesting
    lateinit var repository: UserRepository

    private lateinit var adapter: UserListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ListUserFragmentBinding.inflate(inflater, container, false)
        configureFab()
        configureRecyclerView()
        repository = Injection.createUserRepository()

        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    // CONFIGURATION ---
    private fun configureRecyclerView() {
        adapter = UserListAdapter(this)
        binding.activityListUserRv.adapter = adapter
    }

    private fun configureFab() {
        binding.activityListUserFab.setOnClickListener { view: View? ->
            repository.generateRandomUser()
            loadData()
        }
    }

    private fun loadData() {
        adapter.updateList(Injection.createUserRepository().users)
    }

    // ACTIONS ---
    override fun onClickDelete(user: User) {
        Log.d(ListUserActivity::class.java.name, "User tries to delete a item.")
        repository.deleteUser(user)
        loadData()
    }
}