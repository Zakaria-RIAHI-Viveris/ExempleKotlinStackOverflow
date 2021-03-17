package com.viveris.exemplekotlinstackoverflow.network

import com.viveris.exemplekotlinstackoverflow.ExempleKotlinStackOverflowApplication
import com.viveris.exemplekotlinstackoverflow.model.Users
import com.viveris.exemplekotlinstackoverflow.ui.FetchUserCallback
import kotlinx.coroutines.*

class CoroutineRetrofitRequestManager(
        private val listener: FetchUserCallback,
        private val coroutineScope: CoroutineScope
): IRequestManager {


    /**
     * TODO
     *
     * @param application
     */
    override fun fetchUserFromNetwork(application: ExempleKotlinStackOverflowApplication) {
        coroutineScope.launch {
            listener.onSuccess(fetchUsers(application))
        }
    }

    private suspend fun fetchUsers(application: ExempleKotlinStackOverflowApplication): Users {
        return withContext(Dispatchers.IO) {
            application.retrofit.create(RequestInterface::class.java).fetchCoroutineUsers()
        }
    }
}