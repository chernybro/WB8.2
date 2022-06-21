package com.chernybro.wb52.data.repository

import com.chernybro.wb52.domain.models.HeroDetailsItem
import com.chernybro.wb52.domain.models.HeroItem

interface HeroesListRepository {

    suspend fun getHeroes(): List<HeroItem>

    suspend fun getHero(id: String): HeroDetailsItem
}