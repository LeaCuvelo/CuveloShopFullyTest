package com.cuvelo.shopfully.test.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.cuvelo.shopfully.test.BuildConfig
import com.cuvelo.shopfully.test.R
import com.cuvelo.shopfully.test.analytics.StreamFully
import com.cuvelo.shopfully.test.analytics.StreamFullyEvent
import com.cuvelo.shopfully.test.databinding.FragmentDetailBinding
import com.cuvelo.shopfully.test.ui.main.MainActivity
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.HashMap

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()

    private lateinit var streamFully: StreamFully
    private var streamFullyFlyerSessionBenchmark  = 0L

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

        (activity as MainActivity).hideReadFilterIcon()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with(binding) {
            tvFylerTitleDetail.text = args.title

            Picasso.get()
                .load("https://it-it-media.shopfully.cloud/images/volantini/${args.id}@3x.jpg")
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_error)
                .into(ivFlyerImageDetail)
        }

    }

    //region StreamFully

    override fun onStart() { //Called when UI is visible to user
        super.onStart()

        //Start Session Benchmark
        val date = Date()
        val timeMilli = date.time
        streamFullyFlyerSessionBenchmark = timeMilli

        streamFully = StreamFully(
            requireContext().applicationInfo.packageName,
            BuildConfig.VERSION_NAME)

        val flyerOpenEventMap = HashMap<String, Any>()

        flyerOpenEventMap["Retailer Id"] = args.retailerId
        flyerOpenEventMap["Flyer Id"] = args.id
        flyerOpenEventMap["Position"] = args.position
        flyerOpenEventMap["Retailer First Read"] = !args.firstRead


        val streamFullyEvent = object : StreamFullyEvent {
            override val eventType: String
                get() = "flyer_open"
            override val attributes: Map<String, Any>
                get() = flyerOpenEventMap
        }

        streamFully.process(streamFullyEvent)
    }

    override fun onStop() { //Called when UI is no longer visible to user
        super.onStop()

        //Finish Session Benchmark
        val date = Date()
        val sessionFinished = date.time

        val elapsedTimeMillis = sessionFinished - streamFullyFlyerSessionBenchmark

        val flyerOpenSessionMap = HashMap<String, Any>()
        flyerOpenSessionMap["Flyer Id"] = args.id
        flyerOpenSessionMap["Session Duration"] = elapsedTimeMillis
        flyerOpenSessionMap["Retailer First Read"] = !args.firstRead

        val streamFullyEvent = object : StreamFullyEvent {
            override val eventType: String
                get() = "flyer_session"
            override val attributes: Map<String, Any>
                get() = flyerOpenSessionMap
        }

        streamFully.process(streamFullyEvent)
    }

    //endregion StreamFully


}