package com.lovetocode.diseasesymptoms.models

data class CountryCovidUpdatesDAO(
    var country:String,
    var code:String,
    var confirmed:String,
    var recovered:String,
    var critical:String,
    var deaths:String,
    var latitude:String,
    var longitude:String,
    var lastChange:String,
    var lastUpdate:String
)