package com.example.mytestapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.example.mytestapp.R
import com.example.mytestapp.common.NetworkConnectionListener
import com.example.mytestapp.databinding.ActivityMainBinding
import com.example.mytestapp.ui.network.NoConnectionDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val networkListener by lazy { NetworkConnectionListener() }
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        setupActionBarWithNavController(this, navController)

        val dialog = NoConnectionDialog(this)
        lifecycleScope.launchWhenStarted {
            networkListener.checkNetworkAvailability(this@MainActivity).collect { status ->
                when (status) {
                    true -> dialog.dismiss()
                    false -> dialog.show()
                }
            }
        }
    }
}