package com.lovetocode.diseasesymptoms

import com.google.common.truth.Truth.assertThat
import com.lovetocode.diseasesymptoms.others.Constants
import com.lovetocode.diseasesymptoms.utils.DateTimeUtils
import com.montymobile.utils.NetworkUtils
import org.junit.Test

class UnitTestsAndroid
{
    @Test
    fun date_not_empty()
    {
        assertThat(DateTimeUtils.getCurrentDateByFormat(Constants.DATE_FORMAT_NEW)).isNotEmpty()
    }

    @Test
    fun date_empty()
    {
        assertThat(DateTimeUtils.getCurrentDateByFormat("")).isEmpty()
    }

    @Test
    fun date_convert_empty()
    {
        assertThat(DateTimeUtils.convertDateByFormat(Constants.DATE_FORMATE_OLD,Constants.DATE_FORMAT_NEW,DateTimeUtils.getCurrentDateByFormat(Constants.DATE_FORMATE_OLD))).isEmpty()
    }

    @Test
    fun date_convert_not_empty()
    {
        assertThat(DateTimeUtils.convertDateByFormat(Constants.DATE_FORMATE_OLD,Constants.DATE_FORMAT_NEW,DateTimeUtils.getCurrentDateByFormat(Constants.DATE_FORMATE_OLD))).isNotEmpty()
    }
}