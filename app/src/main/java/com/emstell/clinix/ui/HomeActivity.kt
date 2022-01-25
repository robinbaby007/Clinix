package com.emstell.clinix.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emstell.clinix.R
import com.emstell.clinix.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var homeBinding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)
        supportActionBar?.hide()


    }
}