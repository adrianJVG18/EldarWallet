package com.adrian.eldarwallet.application

import android.content.SharedPreferences
import com.adrian.eldarwallet.presentation.model.AuthUser
import javax.inject.Inject

class AuthenticatedUser @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    companion object {
        private const val USER_ID_KEY = "eldarwallet.userId"
        private const val USER_NAME_KEY = "eldarwallet.user_name"
        private const val USER_LAST_NAME_KEY = "eldarwallet.user_last_name"
    }

    var data: AuthUser
        get() =
            AuthUser(
                id = sharedPreferences.getLong(USER_ID_KEY, 0),
                name = sharedPreferences.getString(USER_NAME_KEY, "") ?: "",
                lastName = sharedPreferences.getString(USER_LAST_NAME_KEY, "") ?: ""
            )
        set(value) {
            sharedPreferences.edit()
                .putLong(USER_ID_KEY, value.id)
                .putString(USER_NAME_KEY, value.name)
                .putString(USER_LAST_NAME_KEY, value.lastName)
                .apply()
        }
}