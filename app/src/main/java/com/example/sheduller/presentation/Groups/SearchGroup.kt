package com.example.sheduller.presentation.Groups

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sheduller.R
import com.example.sheduller.databinding.SearchGroupBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class SearchGroup : BottomSheetDialogFragment() {

    private var binding:SearchGroupBinding?=null
    private var loadContent:LoadContent?=null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchGroupBinding.inflate(inflater, container, false)



        binding?.search?.setOnClickListener(View.OnClickListener {
            loadContent?.loadContent(binding?.enterContent?.text?.toString() ?: return@OnClickListener)
            dismiss() //сворачиваем панель

        })


        return binding?.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        //в контексте находим наш listener
        if (context is LoadContent) { //чтобы сохранять информацию которую ввели
            loadContent = context
        }

    }


    //во избижании утечки памяти нам нужно обнулить наш listener
    override fun onDetach() {
        super.onDetach()
        loadContent = null
    }


}