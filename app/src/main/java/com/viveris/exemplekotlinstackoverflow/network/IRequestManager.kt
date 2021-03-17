package com.viveris.exemplekotlinstackoverflow.network

import com.viveris.exemplekotlinstackoverflow.ExempleKotlinStackOverflowApplication

interface IRequestManager {

    fun fetchUserFromNetwork(application: ExempleKotlinStackOverflowApplication)
}