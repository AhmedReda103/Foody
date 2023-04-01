package com.example.foody.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExtendedIngredient(

    @SerializedName("amount")
    val amount: Double? = null,
    @SerializedName("consistency")
    val consistency: String? = null,

    @SerializedName("image")
    val image: String? = null,

    @SerializedName("name")
    val name: String? = null,
    @SerializedName("original")
    val original: String? = null,

    @SerializedName("unit")
    val unit: String? = null
):Parcelable