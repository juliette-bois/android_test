package com.openclassrooms.magicgithub.ui.user_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.openclassrooms.magicgithub.databinding.ItemListUserBinding
import com.openclassrooms.magicgithub.model.User
import com.openclassrooms.magicgithub.utils.UserDiffCallback

class UserListAdapter(private val callback: Listener) : RecyclerView.Adapter<ListUserViewHolder>() {
    // Utiliser le View Binding
    private var _binding: ItemListUserBinding? = null
    private val binding get() = _binding!!

    // FOR DATA ---
    // Ne doit pas être un var
    private var users: MutableList<User> = ArrayList()

    interface Listener {
        fun onClickDelete(user: User)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserViewHolder {
        _binding = ItemListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ListUserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
        holder.bind(users[position], callback)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    // PUBLIC API ---
    fun updateList(newList: MutableList<User>) {
        val diffResult = DiffUtil.calculateDiff(UserDiffCallback(newList, users))
        users = ArrayList(newList)
        diffResult.dispatchUpdatesTo(this)
    }
}