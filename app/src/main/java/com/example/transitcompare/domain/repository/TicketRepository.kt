package com.example.transitcompare.domain.repository

import com.example.transitcompare.data.datasource.remote.model.FilterOptionModel
import com.example.transitcompare.data.datasource.remote.model.TicketModel
import kotlinx.coroutines.flow.Flow

interface TicketRepository {
    suspend fun getTickets() : Flow<List<TicketModel>>

    suspend fun getFilters() : Flow<List<FilterOptionModel>>
}