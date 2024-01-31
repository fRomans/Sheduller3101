//package com.example.sheduller.presentation
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.navigation.findNavController
//import com.example.sheduller.R
//import com.example.sheduller.databinding.FragmentCatalogBinding
//
//
//class Catalog : Fragment() {
//
//    private var binding: FragmentCatalogBinding? = null
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentCatalogBinding.inflate(inflater, container, false)
//
//        binding?.butAccount?.setOnClickListener(View.OnClickListener {
//            view?.findNavController()?.navigate(R.id.action_catalog_to_account)
//        })
//
//
//        return binding?.root
//    }
//
//
//}