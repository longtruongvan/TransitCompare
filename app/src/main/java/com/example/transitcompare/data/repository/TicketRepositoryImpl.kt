package com.example.transitcompare.data.repository

import com.example.transitcompare.data.datasource.remote.apiservice.TicketApiService
import com.example.transitcompare.data.datasource.remote.model.FilterOptionModel
import com.example.transitcompare.data.datasource.remote.model.TicketModel
import com.example.transitcompare.domain.repository.TicketRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TicketRepositoryImpl(private val ticketApiService: TicketApiService) : TicketRepository {
    override suspend fun getTickets(): Flow<List<TicketModel>> {
        return flow{
            emit(ticketApiService.searchTrips())
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getFilters(): Flow<List<FilterOptionModel>> {
        return flow{
            emit(ticketApiService.getFilters())
        }.flowOn(Dispatchers.IO)
    }
}