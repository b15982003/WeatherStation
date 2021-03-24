package com.example.weatherstation.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherData(
    val include_total : Boolean,
    val resource_id : String,
    val fields : List<field>,
    @Json(name = "__extras") val extras: ApiKey,
    val records_format : String,
    val records : List<Records>,
    val limit : Int,
    val offset : Int,
    @Json(name = "_links") val links : Links,
    val total : Int
): Parcelable  {
}

@Parcelize
data class field(
    val info : Info,
    val type : String,
    val id : String
):Parcelable

@Parcelize
data class Info(
    val notes : String,
    val label : String
):Parcelable

@Parcelize
data class ApiKey(
    @Json(name = "api_key") val apiKey: String
):Parcelable

@Parcelize
data class Records(
    @Json(name = "SiteName") val siteName: String,
    @Json(name = "County") val county: String,
    @Json(name = "AQI") val aqi: String,
    @Json(name = "Pollutant") val pollutant: String,
    @Json(name = "Status") val status: String,
    @Json(name = "SO2") val so2: String,
    @Json(name = "CO") val co: String,
    @Json(name = "CO_8hr") val co8hr: String,
    @Json(name = "O3") val o3: String,
    @Json(name = "O3_8hr") val o38hr: String,
    @Json(name = "PM10") val pm10: String,
    @Json(name = "PM2.5") val pm25 : String,
    @Json(name = "NO2") val no2: String,
    @Json(name = "NOx") val nox: String,
    @Json(name = "NO") val no: String,
    @Json(name = "WindSpeed") val windSpeed: String,
    @Json(name = "WindDirec") val windDirec: String,
    @Json(name = "PublishTime") val publishTime: String,
    @Json(name = "PM2.5_AVG") val pm25Avg: String,
    @Json(name = "PM10_AVG") val pm10Avg: String,
    @Json(name = "SO2_AVG") val cosAvg: String,
    @Json(name = "Longitude") val longitude: String,
    @Json(name = "Latitude") val latitude: String,
    @Json(name = "SiteId") val siteId: String,
    @Json(name = "ImportDate") val importDate: String
):Parcelable

@Parcelize
data class Links(
    val start : String,
    val next : String
):Parcelable