package com.chernybro.wb52.data.repository

import android.util.Log
import com.chernybro.wb52.data.local.HeroesStorage
import com.chernybro.wb52.data.models.HeroDTO
import com.chernybro.wb52.data.models.toHeroDetailsItem
import com.chernybro.wb52.data.models.toHeroItem
import com.chernybro.wb52.data.remote.service.HeroListApi
import com.chernybro.wb52.domain.models.HeroDetailsItem
import com.chernybro.wb52.domain.models.HeroItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject


class HeroesListRepositoryImpl(
    private val heroListApi: HeroListApi,
    private val heroesStorage: HeroesStorage
): HeroesListRepository {

    override suspend fun getHeroes(): List<HeroItem> {
        val localHeroes = heroesStorage.getHeroes()
        return if (!localHeroes.isNullOrEmpty()) {
            localHeroes.map { it.toHeroItem()}
        } else {
            val remoteHeroes = heroListApi.getHeroes()
            val itemType = object : TypeToken<List<HeroDTO>>() {}.type
            heroesStorage.saveHeroes(Gson().toJson(remoteHeroes, itemType))
            remoteHeroes.map { it.toHeroItem() }
        }
    }

    override suspend fun getHero(id: String): HeroDetailsItem {
        val localHero = heroesStorage.getHero(id)
        return if (localHero != null) {
            Log.d("TAG", "getHero: $localHero")
            localHero.toHeroDetailsItem()
        } else {
            val remoteHero = heroListApi.getHero(id.toInt()).toHeroDetailsItem()
            remoteHero
        }
    }
}