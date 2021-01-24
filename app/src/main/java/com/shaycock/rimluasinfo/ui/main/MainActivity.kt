package com.shaycock.rimluasinfo.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.shaycock.rimluasinfo.R
import com.shaycock.rimluasinfo.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    private lateinit var tramRecyclerViewAdapter: TramRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.recyclerTrams.apply {
            this.layoutManager = LinearLayoutManager(this.context)
            tramRecyclerViewAdapter = TramRecyclerViewAdapter()
            this.adapter = tramRecyclerViewAdapter
        }

        viewModel.stopInfoMedLD.observe(this) { stopInfo ->
            //If the rest response from luas has an error, notify the user then close the app
            if (stopInfo.error != null) {
                val alertDialog: AlertDialog = AlertDialog.Builder(this@MainActivity).create()
                alertDialog.setTitle(getString(R.string.error_title_oops))
                alertDialog.setMessage(getString(R.string.error_msg_communicate_to_luas))
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getString(R.string.common_ok)) { _, _ -> finish() }
                alertDialog.setOnDismissListener { finish() }
                alertDialog.show()
            }

            //bind the data to the activity
            binding.stopInfo = stopInfo

            //Load the trams to the recycler adapter
            stopInfo.data?.let {
                tramRecyclerViewAdapter.submitList(stopInfo.data!!.getTrams())
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //Replace the menu to add the refresh button
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Detect if the refresh button is pressed
        if (item.itemId == R.id.action_refresh) viewModel.refreshStopInfo()
        return true
    }
}