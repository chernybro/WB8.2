package com.chernybro.wb52.presentation.navigation

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class GeneralCiceroneHolder {
    val cicerone: Cicerone<Router> = Cicerone.create()
}