package com.baksara.app

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.baksara.app.repository.BaksaraRepository
import com.baksara.app.ui.MainViewModel
import com.baksara.app.ui.home.HomeViewModel
import com.baksara.app.ui.kelas.KelasViewModel
import com.baksara.app.ui.soal.baca.BacaViewModel

class ViewModelFactory private constructor(
    private val repository: BaksaraRepository
    ) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repository) as T
            }

            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repository) as T
            }

            modelClass.isAssignableFrom(KelasViewModel::class.java) -> {
                KelasViewModel(repository) as T
            }

            modelClass.isAssignableFrom(BacaViewModel::class.java) -> {
                BacaViewModel(repository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }
}