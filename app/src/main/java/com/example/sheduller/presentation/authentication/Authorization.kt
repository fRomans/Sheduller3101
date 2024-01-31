package com.example.sheduller.presentation.authentication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnKeyListener
import android.view.ViewGroup
import com.example.sheduller.R
import com.example.sheduller.databinding.AuthorizationBinding

import org.koin.androidx.viewmodel.ext.android.viewModel

// ввести 4-х значный код
class Authorization : Fragment(),OnKeyListener {

    private val createUserViewModel: AuthenticationViewModel by viewModel()
    private var binding: AuthorizationBinding?=null
    private var loadContent:LoadContentAuthentication?=null
    private var getPreferences: SharedPreferences? = null
    private var user:String?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = AuthorizationBinding.inflate(inflater, container, false)

        getPreferences = context?.getSharedPreferences(
            "User",
            Context.MODE_PRIVATE
        )

        binding?.enterCode?.setOnKeyListener(this)

        binding?.logout?.setOnClickListener(View.OnClickListener {
            editUser("User")
            user = "User"
            loadContent?.loadContent("logout")

        })



        return binding?.root
    }


    override fun onKey(view: View, i: Int, keyEvent: KeyEvent): Boolean {
        when (view.id) {
            R.id.enterCode -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {


                    if (user?.isNotEmpty() == true) {
                        createUserViewModel.auditUserAuthorization(user ?: return true,
                            binding?.enterCode?.text?.toString() ?: return true, binding?.errorMessages,
                            context ?: return true)

                    }

                    binding?.enterCode?.setText("")


                    return true
                }
            }
        }

        return false
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is LoadContentAuthentication) {
            loadContent = context
        }

    }

    override fun onDetach() {
        super.onDetach()
        loadContent = null

    }


    override fun onStart() { // при запуске получаем данные из SharedPreferences
        super.onStart()
        user = getPreferences?.getString("key", "User")
    }

    override fun onStop() { // при выходе из прилож запись в SharedPreferences данных
        super.onStop()
        var data:String?=null
        if(user == "User") {
            data = "User"
        } else {
            data = user
        }

        editUser(data)

    }


    private fun editUser(valueUser:String?){
        val editor = getPreferences?.edit()
        editor?.putString("key", valueUser)
        editor?.apply()
    }


}