package com.montymobile.interfaces

import com.lovetocode.diseasesymptoms.models.BaseBO
import io.reactivex.Single
import retrofit2.http.*

interface ApiInterface
{
    @GET
    fun getData(@Url url:String):Single<BaseBO>
}