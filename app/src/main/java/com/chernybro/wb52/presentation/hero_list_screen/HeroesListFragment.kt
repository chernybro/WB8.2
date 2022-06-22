package com.chernybro.wb52.presentation.hero_list_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chernybro.wb52.R
import com.chernybro.wb52.databinding.FragmentHeroesListBinding
import com.chernybro.wb52.domain.models.HeroItem
import com.chernybro.wb52.presentation.about.AboutFragment
import com.chernybro.wb52.presentation.hero_details.HeroDetailsFragment
import com.chernybro.wb52.presentation.navigation.GeneralCiceroneHolder
import com.github.terrakok.cicerone.androidx.FragmentScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HeroesListFragment : Fragment() {

    private var _binding: FragmentHeroesListBinding? = null
    private val binding get() = _binding!!

    private val adapter = HeroesListAdapter()
    private var swipeRefreshLayout: SwipeRefreshLayout? = null

    private val vm: HeroesListViewModel by viewModels()

    @Inject
    lateinit var generalCiceroneHolder: GeneralCiceroneHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter.attachClickHandler(object :HeroClickHandler {
            override fun onItemClick(item: HeroItem) {
                val bundle = Bundle()
                bundle.putString(HeroDetailsFragment.KEY_HERO_ID, item.id)
                bundle.putString(HeroDetailsFragment.KEY_HERO_NAME, item.name)
                val fragment = HeroDetailsFragment()
                fragment.arguments = bundle
                generalCiceroneHolder.cicerone.router.navigateTo(FragmentScreen{ fragment })
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeroesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            this.container.adapter = adapter
            swipeRefreshLayout = swipeRefreshView
        }
        swipeRefreshLayout?.setOnRefreshListener { vm.refresh() }
        configureObserving()
        binding.info.setOnClickListener {
            generalCiceroneHolder.cicerone.router.navigateTo(FragmentScreen { AboutFragment() })
        }

    }

    private fun configureObserving(){
        vm.heroes.observe(viewLifecycleOwner) { heroes ->
            adapter.setData(heroes)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}