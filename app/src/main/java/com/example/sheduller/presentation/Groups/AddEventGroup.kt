package com.example.sheduller.presentation.Groups

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.sheduller.R
import com.example.sheduller.databinding.ActivityAddEventGroupBinding
import com.example.sheduller.presentation.EventsDay.EventsViewModel
import com.example.sheduller.presentation.ScreenApp
import org.koin.androidx.viewmodel.ext.android.viewModel


class AddEventGroup : AppCompatActivity() {

    private var binding:ActivityAddEventGroupBinding? = null
    val eventsViewModel: EventsViewModel by viewModel()
    private var idGroup:Int?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEventGroupBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        idGroup = intent.getStringExtra("groupId")?.toInt()

        binding?.createEvent?.setOnClickListener(View.OnClickListener {

            if(binding?.enterDay?.text?.toString() != "" &&
                binding?.enterMonth?.text?.toString() != "" &&
                binding?.enterYear?.text?.toString() != "" && binding?.enterTimeStart?.text.toString() != "" &&
                binding?.enterTimeEnd?.text?.toString() != "" && binding?.enterDescription?.text.toString() != ""){
                addEvent()
            }

            else{
                binding?.messageError?.text = getString(R.string.error_enter_info)
            }
        })
    }

    private fun addEvent(){

        var month:Int?=null

        when(binding?.enterMonth?.text?.toString()){
            "Январь" -> {
                month = 1
            }
            "Февраль" -> {
                month = 2
            }
            "Март" -> {
                month = 3
            }
            "Апрель" -> {
                month = 4
            }
            "Май" -> {
                month = 5
            }
            "Июнь" -> {
                month = 6
            }
            "Июль" -> {
                month = 7
            }
            "Август" -> {
                month = 8
            }
            "Сентябрь" -> {
                month = 9
            }
            "Октябрь" -> {
                month = 10
            }
            "Ноябрь" -> {
                month = 11
            }
            "Декабрь" -> {
                month = 12
            }

            else -> {
                month = binding?.enterMonth?.text?.toString()?.toInt()
            }
        }

        eventsViewModel.createEvent(binding?.enterDay?.text?.toString()?.toInt(),month,
            binding?.enterYear?.text?.toString()?.toInt(),binding?.enterTimeStart?.text?.toString(),
        binding?.enterTimeEnd?.text?.toString(),binding?.enterDescription?.text?.toString(), idGroup, this)

        val intent = Intent(this, ScreenApp::class.java)
        startActivity(intent)

    }

}