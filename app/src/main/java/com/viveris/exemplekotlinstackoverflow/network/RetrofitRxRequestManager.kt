package com.viveris.exemplekotlinstackoverflow.network

import com.viveris.exemplekotlinstackoverflow.ExempleKotlinStackOverflowApplication
import com.viveris.exemplekotlinstackoverflow.ui.FetchUserCallback
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RetrofitRxRequestManager(
        private val listener: FetchUserCallback,
        private val compositeDisposable: CompositeDisposable
): IRequestManager {

    override fun fetchUserFromNetwork(application: ExempleKotlinStackOverflowApplication) {
        compositeDisposable.add(
                application.retrofit.create(RequestInterface::class.java).fetchRxUsers()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { users ->
                                    if (users.userList != null) {
                                        listener.onSuccess(users)
                                    } else {
                                        listener.onError(Exception("Users is null"))
                                    }
                                },
                                { error -> listener.onError(error) }
                        )
        )
    }
}