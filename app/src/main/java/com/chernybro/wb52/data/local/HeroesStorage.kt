package com.chernybro.wb52.data.local

import com.chernybro.wb52.data.models.HeroDTO

interface HeroesStorage {

    fun saveHeroes(heroesJson: String)

    fun getHero(id: String): HeroDTO?

    fun getHeroes(): List<HeroDTO>?
}