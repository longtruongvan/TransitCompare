package com.example.transitcompare.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class FilterOptionModel(
    @SerializedName("icon")
    var icon: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("description")
    var description: String?,
)

data class FilterOptionModelViewData(
    var icon: String?,
    var title: String?,
    var description: String?,
    var isSelected: Boolean?
)

fun FilterOptionModel.toViewData(): FilterOptionModelViewData {
    return FilterOptionModelViewData(
        icon = this.icon,
        title = this.title,
        description = this.description,
        isSelected = false,
    )
}

fun List<FilterOptionModel>.toViewData(): List<FilterOptionModelViewData> {
    return this.map {
        it.toViewData()
    }
}

