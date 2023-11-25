package com.sample.vkoelassign.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.sample.vkoelassign.R
import com.sample.vkoelassign.databinding.ActivityMainBinding
import com.sample.vkoelassign.utility.Utils

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    /**
     * inital setUps
     */
    private fun init() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val findNavController: NavController = navHostFragment.navController


        // Setup Action Bar
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(findNavController)

        // Setup Toolbar
        binding.toolbar.setupWithNavController(findNavController)

        findNavController.addOnDestinationChangedListener { navController, destination, arguments ->
            when (destination.id) {
                R.id.xList_frag -> {
                    binding.toolbar.visibility = View.GONE
                    Utils.showFadeInAnimOnText(
                        this,
                        binding.toolbarTitle,
                        getString(R.string.txt_x_list)
                    )
                    binding.toolbar.title = ""
                    binding.toolbar.visibility = View.GONE
                }

                R.id.xDetail_frag -> {
                    binding.toolbar.visibility = View.VISIBLE
                    Utils.showFadeInAnimOnText(
                        this,
                        binding.toolbarTitle,
                        getString(R.string.txt_x_detail)
                    )
                    binding.toolbar.title = ""
                    binding.toolbar.visibility = View.GONE
                    binding.toolbar.setNavigationIcon(R.drawable.img_arrow_back_white)
                }
            }
        }
    }


}