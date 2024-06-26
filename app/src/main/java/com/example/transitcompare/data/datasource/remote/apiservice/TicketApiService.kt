package com.example.transitcompare.data.datasource.remote.apiservice

import com.example.transitcompare.data.datasource.remote.model.FilterOptionModel
import com.example.transitcompare.data.datasource.remote.model.TicketModel
import retrofit2.http.GET

interface TicketApiService {
    // https://run.mocky.io/v3/4012d266-674e-4434-a47f-162e0f2321cd
    @GET("/v3/4012d266-674e-4434-a47f-162e0f2321cd")
    suspend fun searchTrips() : List<TicketModel>

    // https://run.mocky.io/v3/a911fda6-9192-4a2b-989f-32b924a8449e
    @GET("/v3/a911fda6-9192-4a2b-989f-32b924a8449e")
    suspend fun getFilters(): List<FilterOptionModel>
}