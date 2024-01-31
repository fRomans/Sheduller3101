//package com.example.sheduller.presentation
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.navigation.findNavController
//import com.example.sheduller.R
//import com.example.sheduller.databinding.FragmentAccountBinding
//
//
//class Account : Fragment() {
//
//private var binding: FragmentAccountBinding? = null
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentAccountBinding.inflate(inflater, container, false)
//
//        binding?.butHome?.setOnClickListener(View.OnClickListener {
//            view?.findNavController()?.navigate(R.id.action_account_to_home2)
//        })
//
//        return binding?.root
//    }
//
//
//}