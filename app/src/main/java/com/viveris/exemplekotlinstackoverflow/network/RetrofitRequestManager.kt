package com.viveris.exemplekotlinstackoverflow.network

import androidx.annotation.NonNull
import com.viveris.exemplekotlinstackoverflow.ExempleKotlinStackOverflowApplication
import com.viveris.exemplekotlinstackoverflow.model.Users
import com.viveris.exemplekotlinstackoverflow.ui.FetchUserCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitRequestManager(private val listener: FetchUserCallback) : IRequestManager {

    override fun fetchUserFromNetwork(application: ExempleKotlinStackOverflowApplication) {
        val requetor = application.retrofit.create(RequestInterface::class.java)

        requetor.fetchUsers().enqueue(
                object : Callback<Users> {
                    override fun onResponse(@NonNull call: Call<Users>, @NonNull response: Response<Users>) {
                        val users = response.body()
                        if (users?.userList != null) {
                            listener.onSuccess(users)
                        } else {
                            listener.onError(Exception("Users is null"))
                        }
                    }

                    override fun onFailure(@NonNull call: Call<Users?>, @NonNull t: Throwable) {
                        listener.onError(t)
                    }
                }
        )
    }
}