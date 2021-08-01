package com.example.shestore.Database

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.shestore.Database.DAO.CartWishlistDAO
import com.example.shestore.Database.Entity.CartWishlistEntity

@androidx.room.Database(entities = [CartWishlistEntity::class], version = 2)
abstract class Database : RoomDatabase() {

    abstract fun cartWishListDAO(): CartWishlistDAO

    companion object {
        private var instance: Database? = null

        fun getInstance(context: Context): Database {
            if (instance == null) {
                synchronized(Database::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        Database::class.java, "database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()
                }
            }
            // Used !! because instance is never null because we are initalizing
            return instance!!
        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
//                populateDatabase(instance!!)
            }
        }

        private fun populateDatabase(db: Database) {
            val cartWishListDAO = db.cartWishListDAO()
        }
    }
}