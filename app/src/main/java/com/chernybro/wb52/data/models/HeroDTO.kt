package com.chernybro.wb52.data.models

import com.chernybro.wb52.domain.models.HeroDetailsItem
import com.chernybro.wb52.domain.models.HeroItem
import com.google.gson.annotations.SerializedName

data class HeroDTO(
    val id: String,
    val images: Images,
    val name: String,
    val powerstats: Powerstats,
    val appearance: Appearance,
    val biography: Biography
)

data class Biography(
    @SerializedName("fullName")
    val fullName: String,
    val publisher: String,
    @SerializedName("placeOfBirth")
    val birthPlace: String
)

data class Images(
    val xs: String,
    val sm: String,
    val md: String,
    val lr: String
)

data class Powerstats(
    val intelligence: String,
    val strength: String,
    val speed: String,
    val durability: String,
    val power: String,
)


data class Appearance(
    val race: String,
    val height: List<String>,
    val weight: List<String>
)

fun HeroDTO.toHeroItem(): HeroItem = HeroItem(id = id, image = images.md, name = name)

fun HeroDTO.toHeroDetailsItem(): HeroDetailsItem =
    HeroDetailsItem(
        id = id,
        name = name,
        publisher = biography.publisher,
        imageUrl = images.md,
        intelligence = powerstats.intelligence,
        strength = powerstats.strength,
        speed = powerstats.speed,
        durability = powerstats.durability,
        power = powerstats.power,
        birthPlace = biography.birthPlace,
        height = appearance.height[1],
        weight = appearance.weight[1],
        race = appearance.race,
        fullName = biography.fullName
    )