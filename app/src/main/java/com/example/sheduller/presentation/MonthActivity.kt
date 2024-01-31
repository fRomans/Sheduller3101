package com.example.sheduller.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sheduller.databinding.ActivityMonthBinding

class MonthActivity : AppCompatActivity() {

    private var binding: ActivityMonthBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMonthBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.searchArrowBackMonth?.setOnClickListener{

            val intent = Intent(this@MonthActivity, ScreenApp::class.java)
            startActivity(intent)
            finish()
        }

    }
}