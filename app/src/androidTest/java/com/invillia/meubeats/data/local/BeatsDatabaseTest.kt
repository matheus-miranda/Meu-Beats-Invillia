package com.invillia.meubeats.data.local

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.invillia.meubeats.data.local.dao.HeadphoneDao
import com.invillia.meubeats.util.TestProducts.ENTITY_HEADPHONE
import com.invillia.meubeats.util.TestProducts.ENTITY_HEADPHONE_TWO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class BeatsDatabaseTest {

    private lateinit var dao: HeadphoneDao
    private lateinit var db: BeatsDatabase
    private val headphoneEntity = ENTITY_HEADPHONE

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, BeatsDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.headphoneDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun shouldSaveAndReadProductFromDatabase() = runBlocking {
        dao.insert(headphoneEntity)
        val dbResult = dao.getAll()

        assertThat(dbResult[0]).isEqualTo(headphoneEntity)
    }

    @Test
    @Throws(Exception::class)
    fun shouldDeleteProductFromDatabase() = runBlocking {
        dao.insert(headphoneEntity)
        dao.deleteAll()
        val dbResult = dao.getAll()

        assertThat(dbResult).isEmpty()
    }

    @Test
    @Throws(Exception::class)
    fun shouldDeleteAndInsertTransaction() = runBlocking {
        dao.insert(headphoneEntity)
        dao.deleteAndInsert(ENTITY_HEADPHONE_TWO)
        val dbResult = dao.getAll()

        assertThat(dbResult).contains(ENTITY_HEADPHONE_TWO)
        assertThat(dbResult).doesNotContain(ENTITY_HEADPHONE)
    }
}