package com.lovetocode.diseasesymptoms

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.lovetocode.diseasesymptoms.utils.PreferenceDataStoreUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class InstrumentedStorageTestsAndroid
{
    @Before
    fun add_data_to_storage() {
        runBlocking {
            PreferenceDataStoreUtils.saveLocationDataData(
                ApplicationProvider.getApplicationContext<Context>(), "22.22", "33.33"
            )
        }
    }

    @Test
    fun check_if_data_stored() {
        runBlocking {
            assertThat(
                PreferenceDataStoreUtils.readLatLngData(
                    ApplicationProvider.getApplicationContext<Context>()
                )
            ).isNotEmpty()
        }
    }
}