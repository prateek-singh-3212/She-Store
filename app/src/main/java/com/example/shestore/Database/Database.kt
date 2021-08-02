package com.example.shestore.Database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.shestore.Database.DAO.CartDAO
import com.example.shestore.Database.DAO.WishlistDAO
import com.example.shestore.Database.Entity.CartEntity
import com.example.shestore.Database.Entity.WishlistEntity

@androidx.room.Database(entities = [WishlistEntity::class, CartEntity::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun WishListDAO(): WishlistDAO
    abstract fun CartDAO(): CartDAO

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
            }
        }
    }
}