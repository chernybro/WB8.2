package com.chernybro.wb52.domain.models

data class HeroDetailsItem(
    val id: String,
    val name: String,
    val publisher: String?,
    val imageUrl: String,
    val intelligence: String,
    val strength: String,
    val speed: String,
    val durability: String,
    val power: String,
    val birthPlace: String,
    val height: String,
    val weight: String,
    val race: String?,
    val fullName: String
)

