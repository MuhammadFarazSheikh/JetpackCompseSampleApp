package com.lovetocode.diseasesymptoms.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.android.gms.maps.model.LatLng
import com.montymobile.callsignature.utils.KeyUtils.Companion.DATA_STORE_NAME
import com.montymobile.callsignature.utils.KeyUtils.Companion.LATITUDE
import com.montymobile.callsignature.utils.KeyUtils.Companion.LONGITUDE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

object PreferenceDataStoreUtils {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)

    suspend fun saveLocationDataData(context: Context, lat: String, long: String) =
        context.dataStore.edit {
            it[stringPreferencesKey(LATITUDE)] = lat
            it[stringPreferencesKey(LONGITUDE)] = long
        }

    suspend fun readLatLngData(context: Context) = context.dataStore.data.let {
        it.first().get(stringPreferencesKey(LATITUDE))+"/"+it.first().get(stringPreferencesKey(LONGITUDE))
    }
}