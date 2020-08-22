package com.demo.branch.di

import com.demo.branch.repositry.AppDataSourceGateway
import com.demo.branch.repositry.AppRemoteDataSourceGatewayImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ApiModule {
    @Binds
    abstract fun provideAppRemoteDataSource(appRemoteDataSourceImpl: AppRemoteDataSourceGatewayImpl): AppDataSourceGateway


}
