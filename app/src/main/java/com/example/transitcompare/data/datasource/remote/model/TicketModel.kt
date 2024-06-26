package com.example.transitcompare.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class TicketModel(
    var id: String,
    var provider: String,
    var departureTime: String,
    var arrivalTime: String,
    var price: Double,
    var totalTime: String,
    var departureLocation: String,
    var destination: String,
)

data class TicketDomainModel(
    var id: String,
    var provider: String,
    var departureTime: String,
    var arrivalTime: String,
    var totalTime: String,
    var departureLocation: String,
    var destination: String,
    var price: Double
)

data class TicketViewData(
    var id: String,
    var provider: String,
    var departureTime: String,
    var arrivalTime: String,
    var price: Double,
    var totalTime: String,
    var departureLocation: String,
    var destination: String,
    var viewDetail: Boolean,
)

fun TicketModel.toDomainModel(): TicketDomainModel {
    return TicketDomainModel(
        id = id,
        provider = provider,
        departureTime = departureTime,
        arrivalTime = arrivalTime,
        price = price,
        totalTime = totalTime,
        departureLocation = departureLocation,
        destination = destination,
    );
}

fun TicketModel.toViewData(): TicketViewData {
    return TicketViewData(
        id = id,
        provider = provider,
        departureTime = departureTime,
        arrivalTime = arrivalTime,
        price = price,
        totalTime = totalTime,
        departureLocation = departureLocation,
        destination = destination,
        viewDetail = false,
    )
}

fun List<TicketModel>.toViewData(): List<TicketViewData> {
    return this.map {
        it.toViewData()
    };
}