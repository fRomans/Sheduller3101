package com.example.sheduller.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sheduller.databinding.ActivitySearchBinding
import java.util.*


class SearchActivity : AppCompatActivity() {

private var binding: ActivitySearchBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.searchArrowBackSearch?.setOnClickListener{

            val intent = Intent(this@SearchActivity, ScreenApp::class.java)
            startActivity(intent)
            finish()
        }

    }
}