package com.cuvelo.shopfully.test.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.cuvelo.shopfully.test.R
import com.cuvelo.shopfully.test.databinding.FragmentMainBinding
import com.cuvelo.shopfully.test.ui.utils.isOnline
import dagger.hilt.android.AndroidEntryPoint

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

        binding.rvFlyers.layoutManager = GridLayoutManagerWrapper(requireContext())

        flyersAdapter = FlyersAdapter(::navigateToDetail)
        binding.rvFlyers.adapter = flyersAdapter

        viewModel.flyers.observe(viewLifecycleOwner) { flyers ->
            flyersAdapter.updateItems(flyers)
        }

        viewModel.progressBarVisibility.observe(viewLifecycleOwner){
            if(it){
                binding.pbLoading.visibility = View.VISIBLE
            }
            else{
                binding.pbLoading.visibility = View.GONE
            }
        }
    }

    private fun navigateToDetail(flyerTitle: String, flyerId: String, flyerRetailerId: String, flyerRead: Boolean, position: Int){
        if(isOnline(context)){
            viewModel.markAsRead(flyerId)
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(flyerTitle, flyerId, flyerRetailerId, position, flyerRead)
            findNavController().navigate(action)
        }
        else{
            Toast.makeText(context,getString(R.string.no_connection), Toast.LENGTH_LONG).show()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFlyers()
        (activity as MainActivity).showReadFilterIcon()
        (activity as MainActivity).unselectedFilterIcon()

    }

    fun filterReadFlyers(){
        viewModel.filterReadFlyers()
    }

    fun removeFilterReadFlyers(){
        viewModel.getFlyers()
    }

}