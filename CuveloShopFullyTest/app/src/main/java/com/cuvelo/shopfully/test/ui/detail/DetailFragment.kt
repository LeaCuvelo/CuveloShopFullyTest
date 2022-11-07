package com.cuvelo.shopfully.test.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.cuvelo.shopfully.test.R
import com.cuvelo.shopfully.test.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_detail,
            container,
            false)

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        with(binding) {
            tvFylerTitleDetail.text = args.title

            Picasso.get()
                .load("https://it-it-media.shopfully.cloud/images/volantini/${args.id}@3x.jpg")
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_error)
                .into(binding.ivFlyerImageDetail)
        }

    }


}