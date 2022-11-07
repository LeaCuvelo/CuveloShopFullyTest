package com.cuvelo.shopfully.test.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import com.cuvelo.shopfully.test.R
import com.cuvelo.shopfully.test.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var filterState = false

    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash: SplashScreen = installSplashScreen()
        screenSplash.setKeepOnScreenCondition{
            false
        }

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.ivFilterIcon.setOnClickListener {
            val mainFragment = getMainFragment()
            if(!filterState){

                mainFragment?.filterReadFlyers()
                filterState = true
            }
            else{
                mainFragment?.removeFilterReadFlyers()
                filterState = false
            }
        }
    }

    private fun getMainFragment(): MainFragment? {
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        navHost?.let { navFragment ->
            navFragment.childFragmentManager.primaryNavigationFragment?.let { fragment ->
                if (fragment is MainFragment) {
                    return fragment
                }
            }
        }
        return null
    }

}