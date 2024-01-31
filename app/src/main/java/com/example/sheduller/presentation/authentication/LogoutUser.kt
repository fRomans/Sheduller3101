package com.example.sheduller.presentation.authentication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sheduller.R
import com.example.sheduller.databinding.LogoutUserBinding

 // экран авторизация/регистрация
class LogoutUser : Fragment(),View.OnClickListener{

    private var binding: LogoutUserBinding? = null
    private var loadContent:LoadContentAuthentication?=null
    private var user:String? = null
    private var getPreferences: SharedPreferences? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = LogoutUserBinding.inflate(inflater, container, false)

        getPreferences = context?.getSharedPreferences(
            "User",
            Context.MODE_PRIVATE
        )
        binding?.authorization?.setOnClickListener(this)
        binding?.registration?.setOnClickListener(this)
        return binding?.root
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.authorization -> {
                if (user=="User") { // значит мы не залогинены (загрузится форма имя/пароль)
                    loadContent?.loadContent("logout_authorization")
                } else {
                    loadContent?.loadContent("authorization")
                }
            }

            R.id.registration -> {
                loadContent?.loadContent("registration")
            }
        }
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

    override fun onStart() {
        super.onStart()
        user = getPreferences?.getString("key", "User")//"User" - значит не залогинен
    }

    override fun onStop() {
        super.onStop()
        var data:String? = null //обнуляем getPreferences

        if(getPreferences?.getString("key", "User") == "User") { // если User - оставляем User
            data = "User"
        } else {
            data = getPreferences?.getString("key", "User") // если не User, то делаем User
        }

        val editor = getPreferences?.edit() // и затем редактируем
        editor?.putString("key", data)
        editor?.apply()

    }


}