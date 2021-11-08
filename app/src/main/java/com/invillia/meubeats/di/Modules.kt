package com.invillia.meubeats.di

import com.invillia.meubeats.data.remote.api.BeatsApi
import com.invillia.meubeats.data.remote.api.RetrofitBuilder
import com.invillia.meubeats.domain.usecase.getNetworkHeadphonesUseCase
import org.koin.dsl.module

private val useCaseModule = module {
    factory { getNetworkHeadphonesUseCase(repository = get()) }
}

private val networkModule = module {
    single { RetrofitBuilder.createService().create(BeatsApi::class.java) }
}

object AppModules {
    val modules = useCaseModule + networkModule
}