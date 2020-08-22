

package com.demo.branch.di

import com.demo.branch.storage.SharedPreferencesStorage
import com.demo.branch.storage.Storage
import dagger.Binds
import dagger.Module

@Module
abstract class StorageModule {

    @Binds
    abstract fun provideStorage(storage: SharedPreferencesStorage): Storage
}
