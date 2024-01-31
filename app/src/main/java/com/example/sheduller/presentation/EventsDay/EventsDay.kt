package com.example.sheduller.presentation.EventsDay

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sheduller.R
import com.example.sheduller.databinding.EventsDayBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class EventsDay : BottomSheetDialogFragment() {

    private var binding:EventsDayBinding? = null
    private var day:Int?=null
    private var month:Int?=null
    private var year:Int?=null
    private var adapter: EventsAdapter? = null
    val eventsViewModel: EventsViewModel by viewModel()
    var groupId:Int?=null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = EventsDayBinding.inflate(inflater, container, false)

        day =  arguments?.getInt("day")
        month =  arguments?.getInt("month")
        year =  arguments?.getInt("year")
        binding?.headerDay?.text = "${day.toString()}-${month.toString()}-${year.toString()}"
        groupId =  arguments?.getInt("groupId")
        initRecyclerEvents()
        loadEventsDay()

        binding?.exit?.setOnClickListener(View.OnClickListener {
            dismiss()
        })
        return binding?.root
    }


    private fun initRecyclerEvents(){
        binding?.recyclerEvents?.layoutManager = LinearLayoutManager(context)
        adapter = EventsAdapter()
        binding?.recyclerEvents?.adapter = adapter
    }

    private fun loadEventsDay(){       // (day ?: return) и  month!! - это одно и то же, но первое более правильно
        eventsViewModel.loadEventsDay(day ?: return, month!!, year!!, groupId!!).observe(viewLifecycleOwner, Observer {
            adapter?.setList(it)
            adapter?.notifyDataSetChanged()
        })


    }




}