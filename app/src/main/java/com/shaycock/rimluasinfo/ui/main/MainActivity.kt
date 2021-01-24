package com.shaycock.rimluasinfo.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
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
            binding.stopInfo = stopInfo
            tramRecyclerViewAdapter.submitList(stopInfo.getInboundTrams())
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