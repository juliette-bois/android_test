package com.openclassrooms.magicgithub.ui.user_list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.openclassrooms.magicgithub.databinding.ItemListUserBinding
import com.openclassrooms.magicgithub.model.User

class ListUserViewHolder(var binding: ItemListUserBinding) : RecyclerView.ViewHolder(binding.root) {

    // Utiliser le view binding
    fun bind(user: User, callback: UserListAdapter.Listener) {
        Glide.with(binding.root)
            .load(user.avatarUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(binding.itemListUserAvatar)

        binding.itemListUserUsername.text = user.login
        binding.itemListUserDeleteButton.setOnClickListener { view: View? -> callback.onClickDelete(user) }
    }
}
