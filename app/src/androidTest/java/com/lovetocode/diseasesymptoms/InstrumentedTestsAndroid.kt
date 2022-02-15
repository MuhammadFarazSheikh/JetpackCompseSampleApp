package com.lovetocode.diseasesymptoms

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.lovetocode.diseasesymptoms.utils.CommonUtils
import com.lovetocode.diseasesymptoms.utils.RuntimePermissionUtils
import com.montymobile.utils.NetworkUtils
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InstrumentedTestsAndroid
{
    @Test
    fun check_if_empty_list()
    {
        assertThat(CommonUtils.getCountoriesList(ApplicationProvider.getApplicationContext<Context>())).isEmpty()
    }

    @Test
    fun check_if_not_empty_list()
    {
        assertThat(CommonUtils.getCountoriesList(ApplicationProvider.getApplicationContext<Context>())).isNotEmpty()
    }

    @Test
    fun check_if_size_not_zero_list()
    {
        assertThat(CommonUtils.getCountoriesList(ApplicationProvider.getApplicationContext<Context>()).size>0).isTrue()
    }

    @Test
    fun check_if_network_connected()
    {
        assertThat(NetworkUtils.checkIfInternetConnected(ApplicationProvider.getApplicationContext<Context>())).isTrue()
    }

    @Test
    fun check_if_network_not_connected()
    {
        assertThat(NetworkUtils.checkIfInternetConnected(ApplicationProvider.getApplicationContext<Context>())).isFalse()
    }

    @Test
    fun check_if_permissions_granted()
    {
        assertThat(RuntimePermissionUtils.checkIfLocationPermissionsGranted(ApplicationProvider.getApplicationContext<Context>())).isTrue()
    }

    @Test
    fun check_if_permissions_not_granted()
    {
        assertThat(RuntimePermissionUtils.checkIfLocationPermissionsGranted(ApplicationProvider.getApplicationContext<Context>())).isFalse()
    }
}