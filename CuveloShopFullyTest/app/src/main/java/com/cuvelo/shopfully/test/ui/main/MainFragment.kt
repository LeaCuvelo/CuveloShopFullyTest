package com.cuvelo.shopfully.test.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.cuvelo.shopfully.test.R
import com.cuvelo.shopfully.test.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var flyersAdapter: FlyersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_main,
            container,
            false)


        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        flyersAdapter = FlyersAdapter(viewModel::onFlyerClicked)
        binding.rvFlyers.adapter = flyersAdapter

        viewModel.flyers.observe(viewLifecycleOwner) { flyers ->
            flyersAdapter.updateItems(flyers.data)
        }

    }


}