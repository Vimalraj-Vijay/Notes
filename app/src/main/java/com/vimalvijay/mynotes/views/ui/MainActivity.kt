package com.vimalvijay.mynotes.views.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.vimalvijay.mynotes.R
import com.vimalvijay.mynotes.databinding.ActivityMainBinding
import com.vimalvijay.mynotes.utils.Constants
import com.vimalvijay.mynotes.utils.Constants.ACT_ADD
import com.vimalvijay.mynotes.utils.Constants.KEY_ACTION_TYPE
import com.vimalvijay.mynotes.utils.gone
import com.vimalvijay.mynotes.utils.visible

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        instance = this
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment
        navController = navHostFragment.navController
        initHeader()

    }

    private fun initHeader() {
        mainBinding.vHeader.ivHeaderBack.gone()
        mainBinding.vHeader.tvHeaderTitle.text = resources.getString(R.string.notes)
        mainBinding.vHeader.ivHeaderBack.setOnClickListener {
            onBackPressed()
        }
    }

    fun hideHeader() {
        mainBinding.vHeader.cltVHeader.gone()
    }

    fun showHeader(headerTitle: String, isBackVisible: Boolean) {
        mainBinding.vHeader.cltVHeader.visible()
        mainBinding.vHeader.tvHeaderTitle.text = headerTitle
        if (isBackVisible) {
            mainBinding.vHeader.ivHeaderBack.visible()
        } else {
            mainBinding.vHeader.ivHeaderBack.gone()
        }
    }

    fun onAddNewNotesClick(bundle: Bundle) {
        navController.navigate(R.id.action_notesFragment_to_updateNotesFragment,bundle)
    }

    fun onEditNotesClick(bundle: Bundle) {
        navController.navigate(R.id.action_notesFragment_to_updateNotesFragment, bundle)
    }

    companion object {
        private var instance: MainActivity? = null

        fun getMainInstance(): MainActivity {
            return instance!!
        }
    }

}