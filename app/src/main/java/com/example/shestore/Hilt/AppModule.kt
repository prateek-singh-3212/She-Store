package com.example.shestore.Hilt

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.shestore.MainActivity
import com.example.shestore.ViewModel.ItemDetailViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Provides
//    fun provideItemDetailViewModelInstance(
//        @ApplicationContext context: Context
//    ): ItemDetailViewModel = ViewModelProvider(context as MainActivity).get(ItemDetailViewModel::class.java)
}