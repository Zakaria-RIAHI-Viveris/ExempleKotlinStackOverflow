package com.viveris.exemplekotlinstackoverflow.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.viveris.exemplekotlinstackoverflow.databinding.ActivityDetailBinding
import com.viveris.exemplekotlinstackoverflow.model.User

class DetailActivity : AppCompatActivity() {

    companion object {
        const val INPUT_EXTRA_USER_KEY = "INPUT_EXTRA_USER_KEY"
    }

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeData()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }


    private fun initializeData() {
        intent.extras?.let { bundle ->
            val user = bundle.getParcelable<User>(INPUT_EXTRA_USER_KEY)
            binding.userDetail.text = user.toString()
        }
    }
}