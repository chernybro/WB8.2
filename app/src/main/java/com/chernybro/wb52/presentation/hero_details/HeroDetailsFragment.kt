package com.chernybro.wb52.presentation.hero_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.chernybro.wb52.R
import com.chernybro.wb52.databinding.FragmentHeroDetailsBinding
import com.chernybro.wb52.presentation.navigation.GeneralCiceroneHolder
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HeroDetailsFragment : Fragment() {

    private var _binding: FragmentHeroDetailsBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val KEY_HERO_ID = "KEY_HERO_ID"
        const val KEY_HERO_NAME = "KEY_HERO_NAME"
    }

    private val vm: HeroDetailsViewModel by viewModels()

    @Inject
    lateinit var generalCiceroneHolder: GeneralCiceroneHolder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHeroDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(KEY_HERO_ID)?.let { vm.getHeroDetails(it.toInt()) }
        vm.heroDetails.observe(viewLifecycleOwner) { hero ->
            binding.apply {
                heroName.text = hero.name
                Picasso.get().load(hero.imageUrl).into(banner)
                tvDurability.text = getString(R.string.durability_variant, hero.durability)
                tvHeight.text = getString(R.string.height_variant, hero.height)
                tvWeight.text = getString(R.string.weight_variant, hero.weight)
                tvPower.text = getString(R.string.power_variant, hero.power)
                tvIntelligenceStats.text = getString(R.string.intelligence_variant, hero.intelligence)
                tvStrengthStats.text = getString(R.string.strength_variant, hero.strength)
                tvPlaceOfBirth.text = getString(R.string.birth_place_variant, hero.birthPlace)
                tvRace.text = getString(R.string.race_variant, hero.race)
                tvFullName.text = hero.fullName
                tvPublisher.text = getString(R.string.publisher_variant, hero.publisher)
                tvSpeed.text = getString(R.string.speed_variant, hero.speed)
            }
        }
        (arguments?.getString(KEY_HERO_NAME, getString(R.string.hero_name)) ?: getString(R.string.hero_name)).also { binding.toolbarTitle.text = it }

        binding.toolbar.setNavigationOnClickListener { generalCiceroneHolder.cicerone.router.exit() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}