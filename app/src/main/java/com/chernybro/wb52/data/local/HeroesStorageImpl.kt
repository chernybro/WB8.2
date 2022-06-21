package com.chernybro.wb52.data.local

import android.content.SharedPreferences
import android.util.Log
import com.chernybro.wb52.data.models.HeroDTO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HeroesStorageImpl(
    private val sharedPreferences: SharedPreferences
) : HeroesStorage {

    companion object {
        private const val HEROES_KEY = "HEROES_KEY"
    }

    override fun saveHeroes(heroesJson: String) {
        sharedPreferences.edit().putString(HEROES_KEY, heroesJson).apply()
    }

    override fun getHero(id: String): HeroDTO? {
        val savedJson = sharedPreferences.getString(HEROES_KEY, "")
        if ((savedJson ?: "").isEmpty()) return null
        val itemType = object : TypeToken<List<HeroDTO>>() {}.type
        val heroesList = Gson().fromJson<List<HeroDTO>>(savedJson, itemType)
        Log.d("TAG", "getHero: list = $id")
        return heroesList.first { heroDTO -> heroDTO.id == id }
    }

    override fun getHeroes(): List<HeroDTO>? {
        val savedJson = sharedPreferences.getString(HEROES_KEY, "")
        if ((savedJson ?: "").isEmpty()) return emptyList()
        val itemType = object : TypeToken<List<HeroDTO>>() {}.type
        return Gson().fromJson(savedJson, itemType)
    }
}