package com.invillia.meubeats.di

import com.invillia.meubeats.domain.usecase.getNetworkHeadphonesUseCase
import org.koin.dsl.module

private val useCaseModule = module {
    factory { getNetworkHeadphonesUseCase(repository = get()) }
}

object AppModules {
    val modules = useCaseModule
}