package com.example.e_comm.data.DI

import android.content.Context
import androidx.room.Room
import com.example.e_comm.data.Localdata.AppDatabase
import com.example.e_comm.data.Localdata.ItemsDao
import com.example.e_comm.data.remotedata.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module{

    @Provides
    fun provideGymApiService(retrofit: Retrofit):ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .baseUrl("https://e-comm-95a1c-default-rtdb.firebaseio.com/")
            .build()
    }

    @Provides
    fun provideRoomDao(
        db: AppDatabase
    ): ItemsDao {
        return db.dao
    }

    @Singleton
    @Provides
    fun providesRoomDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "Item_db"
        ).fallbackToDestructiveMigration().build()
    }
}