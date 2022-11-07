package com.cuvelo.shopfully.test.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.cuvelo.shopfully.test.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    private var filterState = false

    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash: SplashScreen = installSplashScreen()
        screenSplash.setKeepOnScreenCondition{
            false
        }

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {

            setSupportActionBar(toolbar)

            ivFilterIcon.setOnClickListener {
                if(!filterState){
                    mainActivityViewModel.setShowReadFilteredFlyersValue(true)
                    ivFilterIcon.isSelected = true
                    filterState = true
                }
                else{
                    mainActivityViewModel.setShowReadFilteredFlyersValue(false)
                    ivFilterIcon.isSelected = false
                    filterState = false
                }
            }
        }

        //Setup Observers
        mainActivityViewModel.filterIconVisibility.observe(this) { visibility ->
            binding.ivFilterIcon.visibility = if (visibility) View.VISIBLE else View.GONE
        }

        mainActivityViewModel.filterIconSelected.observe(this) { selected ->
            if(selected){
                binding.ivFilterIcon.isSelected = true
                filterState = true

            }else{
                binding.ivFilterIcon.isSelected = false
                filterState = false
            }
        }

    }

}