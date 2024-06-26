package com.example.transitcompare.core.modules

import com.example.transitcompare.core.coroutines.DispatcherProvider
import com.example.transitcompare.core.coroutines.DispatcherProviderImpl
import com.example.transitcompare.data.datasource.remote.utils.RetrofitClient
import com.example.transitcompare.data.repository.TicketRepositoryImpl
import com.example.transitcompare.domain.repository.TicketRepository
import com.example.transitcompare.presentation.home.viewmodel.HomeViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val endpointModule = module {
    single { RetrofitClient.getTicketApiService() }
}
val repositoryModule = module {
    single<TicketRepository> { TicketRepositoryImpl(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModelImpl(get(), get()) }
}

val appModule  = module {
    factory<DispatcherProvider> { DispatcherProviderImpl() }
}
val allModules = listOf(appModule, endpointModule, repositoryModule, viewModelModule)