package com.example.finanzas.data.di

import android.content.Context
import androidx.room.Room
import com.example.finanzas.data.RepositoryImpl
import com.example.finanzas.data.database.FinanzasDB
import com.example.finanzas.data.database.dao.CategoryDao
import com.example.finanzas.data.database.dao.MovementsDao
import com.example.finanzas.data.database.dao.TypeCategoriesDao
import com.example.finanzas.data.database.dao.UsersDao
import com.example.finanzas.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class room {

    private val PARAMETERS_NAME = "finanzas_db"

    /**
     * Proporciona una instancia de la base de datos de Room.
     *
     * @param context El contexto de la aplicación.
     * @return Una instancia de [ParametersDatabase].
     */
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, FinanzasDB::class.java, PARAMETERS_NAME).build()

    /**
     * Proporciona una instancia del objeto DAO para la tabla de parámetros.
     *
     * @return Una instancia de [ParametersDao].
     */
    @Singleton
    @Provides
    fun provideTypeCategoriesDao(db: FinanzasDB) = db.getTypeCategoriesDao()

    /**
     * Proporciona una instancia del objeto DAO para la tabla de parámetros.
     *
     * @return Una instancia de [ParametersDao].
     */
    @Singleton
    @Provides
    fun provideCategoryDao(db: FinanzasDB) = db.getCategoryDao()


    /**
     * Proporciona una instancia del objeto DAO para la tabla de parámetros.
     *
     * @return Una instancia de [UserDao].
     */
    @Singleton
    @Provides
    fun provideUserDao(db: FinanzasDB) = db.getUsers()

    /**
     * Proporciona una instancia del objeto DAO para la tabla de parámetros.
     *
     * @return Una instancia de [UserDao].
     */
    @Singleton
    @Provides
    fun provideMovementsDao(db: FinanzasDB) = db.getMovements()


    @Singleton
    @Provides
    fun provideRepository(
        usersDao: UsersDao,
        categoryDao: CategoryDao,
        movementsDao: MovementsDao,
        typeCategoriesDao: TypeCategoriesDao
    ): Repository {
        return RepositoryImpl(usersDao, categoryDao, movementsDao, typeCategoriesDao)
    }


}