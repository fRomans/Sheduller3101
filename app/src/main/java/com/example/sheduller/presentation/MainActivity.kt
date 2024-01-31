package com.example.sheduller.presentation

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sheduller.databinding.ActivityMainBinding
import com.example.sheduller.R
import com.example.sheduller.presentation.authentication.Authorization
import com.example.sheduller.presentation.authentication.LoadContentAuthentication
import com.example.sheduller.presentation.authentication.LogoutAuthorization
import com.example.sheduller.presentation.authentication.LogoutUser
import com.example.sheduller.presentation.authentication.Registration

class MainActivity : AppCompatActivity(), LoadContentAuthentication {

    private var binding: ActivityMainBinding? = null
    private var getPreferences: SharedPreferences? = null
    private var user:String?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        getPreferences = this.getSharedPreferences(
            "User",
            Context.MODE_PRIVATE
        )


        user = getPreferences?.getString("key", "User")

        if (user!="User") {

            supportFragmentManager.beginTransaction().replace(R.id.contentMainAc, Authorization()).commit()

        } else {

            supportFragmentManager.beginTransaction().replace(R.id.contentMainAc, LogoutUser()).commit()

        }



    }



    override fun loadContent(tag: String) {
        when(tag) {
            "logout" -> {// авториз  или регистр
                supportFragmentManager.beginTransaction().replace(R.id.contentMainAc, LogoutUser()).commit()
            }

            "authorization" -> { // ввести код
                supportFragmentManager.beginTransaction().replace(R.id.contentMainAc, Authorization()).commit()
            }

            "registration" -> { // логин + пароль
                supportFragmentManager.beginTransaction().replace(R.id.contentMainAc, Registration()).commit()
            }

            "logout_authorization" -> {// логин + пароль а авторизации
                supportFragmentManager.beginTransaction().replace(R.id.contentMainAc, LogoutAuthorization()).commit()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        user = getPreferences?.getString("key", "User")
    }




}