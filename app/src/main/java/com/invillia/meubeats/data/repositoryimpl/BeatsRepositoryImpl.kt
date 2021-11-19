package com.invillia.meubeats.data.repositoryimpl

import android.content.Context
import android.os.RemoteException
import com.invillia.meubeats.R
import com.invillia.meubeats.core.Resource
import com.invillia.meubeats.data.local.BeatsDatabase
import com.invillia.meubeats.data.local.entity.HeadphoneEntity
import com.invillia.meubeats.data.mapper.HeadphoneEntityMapper
import com.invillia.meubeats.data.mapper.NetworkHeadphoneMapper
import com.invillia.meubeats.data.mapper.asEntity
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
    db: BeatsDatabase,
    private val networkMapper: NetworkHeadphoneMapper<NetworkHeadphone, Headphone>,
    private val dbMapper: HeadphoneEntityMapper<HeadphoneEntity, Headphone>,
    private val context: Context
) : BeatsRepository {

    private val headphoneDao = db.headphoneDao

    override fun getHeadphones(): Flow<Resource<List<Headphone>>> = flow {
        emit(Resource.Loading())

        // Current cached data in DB
        val localHeadphones = dbMapper.toDomainList(headphoneDao.getAll())
        emit(Resource.Loading(data = localHeadphones))

        try {
            val remoteHeadphones = networkMapper.toDomainList(service.getHeadphones())
            val entityHeadphones = remoteHeadphones.map { dbMapper.toEntity(it) }.toTypedArray()
            headphoneDao.deleteAndInsert(*entityHeadphones)
        } catch (e: HttpException) {
            val error = e.response()?.toString()
            emit(Resource.Error(message = error, data = localHeadphones))
        } catch (i: IOException) {
            emit(Resource.Error(message = i.localizedMessage, data = localHeadphones))
        }

        // New data in DB
        val refreshedCache = dbMapper.toDomainList(headphoneDao.getAll())
        emit(Resource.Success(data = refreshedCache))
    }

    override fun getNetworkHeadphones(): Flow<List<Headphone>> = flow {
        try {
            val headphoneList = networkMapper.toDomainList(service.getHeadphones())
            emit(headphoneList)
        } catch (e: HttpException) {
            val error = e.response()?.toString()
            throw RemoteException(error)
        } catch (i: IOException) {
            throw RemoteException(context.getString(R.string.network_connection_error))
        }
    }

    override suspend fun saveToDb(headphones: Array<Headphone>) {
        headphoneDao.insert(*headphones.asEntity())
    }
}