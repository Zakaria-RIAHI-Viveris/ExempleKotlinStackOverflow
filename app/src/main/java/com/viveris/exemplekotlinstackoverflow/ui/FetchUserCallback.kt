package com.viveris.exemplekotlinstackoverflow.ui

import com.viveris.exemplekotlinstackoverflow.model.Users

interface FetchUserCallback {

    fun onSuccess(users: Users)
    fun onError(throwable: Throwable)
}