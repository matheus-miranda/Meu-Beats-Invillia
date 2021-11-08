package com.invillia.meubeats.di

import com.invillia.meubeats.data.mapper.EntityMapper
import com.invillia.meubeats.data.mapper.NetworkHeadphoneMapper
import com.invillia.meubeats.data.remote.api.BeatsApi
import com.invillia.meubeats.data.remote.api.RetrofitBuilder
import com.invillia.meubeats.data.remote.dto.NetworkHeadphone
import com.invillia.meubeats.data.repositoryimpl.BeatsRepositoryImpl
import com.invillia.meubeats.domain.model.Headphone
import com.invillia.meubeats.domain.repository.BeatsRepository
import com.invillia.meubeats.domain.usecase.GetNetworkHeadphonesUseCase
import org.koin.dsl.module

private val useCaseModule = module {
    factory { GetNetworkHeadphonesUseCase(repository = get()) }
}

private val networkModule = module {
    single { RetrofitBuilder.createService().create(BeatsApi::class.java) }
}

private val mapperModule = module {
    factory<EntityMapper<NetworkHeadphone, Headphone>> { NetworkHeadphoneMapper() }
}

private val repositoryModule = module {
    factory<BeatsRepository> { BeatsRepositoryImpl(service = get(), mapper = get()) }
}

object AppModules {
    val modules = useCaseModule + networkModule + repositoryModule + mapperModule
}