package com.adrian.eldarwallet.module

import android.content.Context
import android.content.SharedPreferences
import com.adrian.eldarwallet.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferenceModule {

    @Singleton
    @Provides
    fun sharedPreference(
        @ApplicationContext applicationContext: Context
    ): SharedPreferences {
        return applicationContext.getSharedPreferences(
            applicationContext.getString(R.string.preference_file_key),
            Context.MODE_PRIVATE
        )
    }

}