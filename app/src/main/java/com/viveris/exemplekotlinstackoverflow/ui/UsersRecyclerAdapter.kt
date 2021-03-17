package com.viveris.exemplekotlinstackoverflow.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.viveris.exemplekotlinstackoverflow.databinding.ItemUserBinding
import com.viveris.exemplekotlinstackoverflow.model.User

class UsersRecyclerAdapter(
        private val usersList: List<User>,
        private val context: Context,
        private val listener: IUserClick
) : RecyclerView.Adapter<UsersRecyclerAdapter.ViewHolder>() {

    private lateinit var binding: ItemUserBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemUserBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(usersList[position])
    }

    override fun getItemCount() = usersList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setData(user: User?) {
            if (user != null) {
                binding.apply {
                    userAlias.text = user.displayName
                    user.badgeCounts?.let {
                        userGoldNumber.text = it.gold.toString()
                        userSilverNumber.text = it.silver.toString()
                        userBronzeNumber.text = it.bronze.toString()
                    }
                    listItem.setOnClickListener { listener.onUserClicked(user) }
                }
            }
        }
    }
}