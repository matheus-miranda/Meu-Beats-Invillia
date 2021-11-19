package com.invillia.meubeats.di

import com.invillia.meubeats.data.local.BeatsDatabase
import com.invillia.meubeats.data.local.entity.HeadphoneEntity
import com.invillia.meubeats.data.mapper.HeadphoneEntityMapper
import com.invillia.meubeats.data.mapper.HeadphoneEntityMapperImpl
import com.invillia.meubeats.data.mapper.NetworkHeadphoneMapper
import com.invillia.meubeats.data.mapper.NetworkHeadphoneMapperImpl
import com.invillia.meubeats.data.remote.api.BeatsApi
import com.invillia.meubeats.data.remote.api.RetrofitBuilder
import com.invillia.meubeats.data.remote.dto.NetworkHeadphone
import com.invillia.meubeats.data.repositoryimpl.BeatsRepositoryImpl
import com.invillia.meubeats.domain.model.Headphone
import com.invillia.meubeats.domain.repository.BeatsRepository
import com.invillia.meubeats.domain.usecase.GetHeadphonesUseCase
import com.invillia.meubeats.domain.usecase.GetNetworkHeadphonesUseCase
import com.invillia.meubeats.presentation.imagecaching.GlideImageCachingImpl
import com.invillia.meubeats.presentation.imagecaching.ImageCaching
import com.invillia.meubeats.presentation.viewmodel.ProductListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val useCaseModule = module {
    factory { GetNetworkHeadphonesUseCase(repository = get()) }
    factory { GetHeadphonesUseCase(repository = get()) }
}

private val dataModule = module {
    single { RetrofitBuilder.createService().create(BeatsApi::class.java) }
    single { BeatsDatabase.getInstance(context = androidApplication()) }
}

private val mapperModule = module {
    factory<NetworkHeadphoneMapper<NetworkHeadphone, Headphone>> { NetworkHeadphoneMapperImpl() }
    factory<HeadphoneEntityMapper<HeadphoneEntity, Headphone>> { HeadphoneEntityMapperImpl() }
}

private val repositoryModule = module {
    factory<BeatsRepository> {
        BeatsRepositoryImpl(
            service = get(),
            db = get(),
            networkMapper = get(),
            dbMapper = get(),
            context = androidContext()
        )
    }
}

private val presentationModule = module {
    factory<ImageCaching> { GlideImageCachingImpl() }
}

private val viewModelModule = module {
    viewModel {
        ProductListViewModel(
            getNetworkHeadphonesUseCase = get(),
            getHeadphonesUseCase = get()
        )
    }
}

object AppModules {
    val modules =
        useCaseModule + dataModule + repositoryModule + mapperModule + presentationModule + viewModelModule
}