package com.lovetocode.diseasesymptoms.models

data class VideoItem (
    val id: String,
    val title: String? = null,
    val description: String,
    val thumbnailURL: String
)