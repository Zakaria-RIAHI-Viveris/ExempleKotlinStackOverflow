package com.viveris.exemplekotlinstackoverflow.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.viveris.exemplekotlinstackoverflow.ExempleKotlinStackOverflowApplication
import com.viveris.exemplekotlinstackoverflow.databinding.ActivityMainBinding
import com.viveris.exemplekotlinstackoverflow.extention.toast
import com.viveris.exemplekotlinstackoverflow.model.User
import com.viveris.exemplekotlinstackoverflow.model.Users
import com.viveris.exemplekotlinstackoverflow.network.CoroutineRetrofitRequestManager
import com.viveris.exemplekotlinstackoverflow.network.IRequestManager
import com.viveris.exemplekotlinstackoverflow.network.RetrofitRxRequestManager
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), FetchUserCallback, IUserClick, CoroutineScope {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private var progressBarManager: ProgressBarManager? = null

    private lateinit var requestManager: IRequestManager

    private val handler = CoroutineExceptionHandler { _, exception ->
        onError(exception)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job + handler

    private lateinit var job: Job

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        progressBarManager = ProgressBarManager(binding.progressBarHolder)
        initializeData()
    }

    override fun onDestroy() {
        job.cancel()
        compositeDisposable.dispose()
        _binding = null
        super.onDestroy()
    }

    private fun initializeData() {
        progressBarManager?.onProgressBarStateChange(true)
        //requestManager = RetrofitRequestManager(this)
        //requestManager = RetrofitRxRequestManager(this, compositeDisposable)
        requestManager = CoroutineRetrofitRequestManager(this, this)
        requestManager.fetchUserFromNetwork(application as ExempleKotlinStackOverflowApplication)
    }

    override fun onSuccess(users: Users) {
        progressBarManager?.onProgressBarStateChange(false)
        toast(this, "success")

        binding.recyclerViewUsers.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, VERTICAL))
            users.userList?.let {
                adapter = UsersRecyclerAdapter(it, applicationContext, this@MainActivity)
            }
        }
    }

    override fun onError(throwable: Throwable) {
        progressBarManager?.onProgressBarStateChange(false)
        toast(this, "error", Toast.LENGTH_LONG)
    }

    override fun onUserClicked(user: User) {
        startActivity(Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.INPUT_EXTRA_USER_KEY, user)
        })
    }
}