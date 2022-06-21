package com.chernybro.wb52.data.remote.service

import com.chernybro.wb52.data.models.HeroDTO
import com.chernybro.wb52.domain.models.HeroDetailsItem
import com.chernybro.wb52.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroListApi {
    @GET("gh/akabab/superhero-api@0.3.0/api/id/{id}.json")
    suspend fun getHero(@Path("id") id: Int): HeroDTO

    @GET("gh/akabab/superhero-api@0.3.0/api/all.json")
    suspend fun getHeroes(): List<HeroDTO>
}