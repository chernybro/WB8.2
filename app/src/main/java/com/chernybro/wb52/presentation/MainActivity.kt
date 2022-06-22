package com.chernybro.wb52.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.chernybro.wb52.R
import com.chernybro.wb52.databinding.ActivityMainBinding
import com.chernybro.wb52.presentation.hero_list_screen.HeroesListFragment
import com.chernybro.wb52.presentation.navigation.GeneralCiceroneHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var generalCiceroneHolder: GeneralCiceroneHolder

    private val navigator = AppNavigator(this, R.id.fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            generalCiceroneHolder.cicerone.router.newRootChain(FragmentScreen{ HeroesListFragment() })
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        generalCiceroneHolder.cicerone.getNavigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        generalCiceroneHolder.cicerone.getNavigatorHolder().removeNavigator()
        super.onPause()
    }
}