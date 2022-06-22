package com.chernybro.wb52.presentation.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chernybro.wb52.R
import com.chernybro.wb52.databinding.FragmentAboutBinding
import com.chernybro.wb52.presentation.navigation.GeneralCiceroneHolder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var generalCiceroneHolder: GeneralCiceroneHolder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.projectLinkValue.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.github_project_link)))
            startActivity(browserIntent)
        }
        binding.toolbar.setNavigationOnClickListener { generalCiceroneHolder.cicerone.router.exit() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}