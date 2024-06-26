package com.example.transitcompare.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import com.example.transitcompare.data.datasource.remote.model.FilterOptionModelViewData
import com.example.transitcompare.data.datasource.remote.model.TicketViewData

interface HomeViewModel {
    val uiState: LiveData<HomeUIState>

    fun getTicket()

    fun getFilters()

    fun loadMoreTickets()

    sealed interface HomeUIState {
        data object ShowLoading : HomeUIState

        data class ShowListOptionFilter(val filters: List<FilterOptionModelViewData>) : HomeUIState
        data class ShowListTicket(val tickets: List<TicketViewData>) : HomeUIState
    }
}