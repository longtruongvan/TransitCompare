package com.example.transitcompare.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.transitcompare.core.base.BaseViewModel
import com.example.transitcompare.core.coroutines.DispatcherProvider
import com.example.transitcompare.data.datasource.remote.model.FilterOptionModelViewData
import com.example.transitcompare.data.datasource.remote.model.TicketViewData
import com.example.transitcompare.data.datasource.remote.model.toViewData
import com.example.transitcompare.domain.repository.TicketRepository
import kotlinx.coroutines.launch

class HomeViewModelImpl(
    private val ticketRepository: TicketRepository,
    private val coroutineDispatcher: DispatcherProvider,
) : BaseViewModel(), HomeViewModel {
    private val _uiState = MutableLiveData<HomeViewModel.HomeUIState>()
    var optionFilters : ArrayList<FilterOptionModelViewData> = arrayListOf()
    var tickets : ArrayList<TicketViewData> = arrayListOf()
    override val uiState: LiveData<HomeViewModel.HomeUIState>
        get() = _uiState

    override fun getTicket() {
        _uiState.postValue(HomeViewModel.HomeUIState.ShowLoading)
        viewModelScope.launch(coroutineDispatcher.io()) {
            ticketRepository.getTickets().collect {
                tickets.addAll(it.toViewData())
                _uiState.postValue(HomeViewModel.HomeUIState.ShowListTicket(it.toViewData()))
            }
        }
    }

    override fun getFilters() {
        viewModelScope.launch(coroutineDispatcher.io()) {
            ticketRepository.getFilters().collect {
                optionFilters.addAll(it.toViewData())
                _uiState.postValue(HomeViewModel.HomeUIState.ShowListOptionFilter(it.toViewData()))
            }
        }
    }

    override fun loadMoreTickets() {
        getTicket()
    }

}