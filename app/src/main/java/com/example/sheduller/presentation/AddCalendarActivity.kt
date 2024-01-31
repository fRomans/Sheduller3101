package com.example.sheduller.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sheduller.R
import com.example.sheduller.databinding.ActivityAddCalendarBinding
import com.example.sheduller.databinding.ActivityMonthBinding

class AddCalendarActivity : AppCompatActivity() {

    private var binding: ActivityAddCalendarBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCalendarBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.searchArrowBack2AddCalendar?.setOnClickListener{

            val intent = Intent(this@AddCalendarActivity, ScreenApp::class.java)
            startActivity(intent)
            finish()
        }

    }
}