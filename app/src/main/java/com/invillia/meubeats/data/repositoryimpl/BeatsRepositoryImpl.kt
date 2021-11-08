package com.invillia.meubeats.data.repositoryimpl

import android.os.RemoteException
import com.invillia.meubeats.data.mapper.EntityMapper
import com.invillia.meubeats.data.remote.api.BeatsApi
import com.invillia.meubeats.data.remote.dto.NetworkHeadphone
import com.invillia.meubeats.domain.model.Headphone
import com.invillia.meubeats.domain.repository.BeatsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class BeatsRepositoryImpl(
    private val service: BeatsApi,
    private val mapper: EntityMapper<NetworkHeadphone, Headphone>
) : BeatsRepository {

    override fun getNetworkHeadphones(): Flow<List<Headphone>> = flow {
        try {
            val headphoneList = mapper.toDomainList(service.getHeadphones())
            emit(headphoneList)
        } catch (e: HttpException) {
            val error = e.response()?.toString()
            throw RemoteException(error)
        } catch (i: IOException) {
            throw RemoteException("Por favor cheque a sua conexão com a internet.")
        }
    }
}