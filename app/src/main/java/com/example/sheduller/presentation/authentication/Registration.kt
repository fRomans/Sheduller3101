package com.example.sheduller.presentation.authentication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sheduller.R
import com.example.sheduller.databinding.RegistrationBinding

import org.koin.androidx.viewmodel.ext.android.viewModel

//регистрация
class Registration : Fragment(),View.OnClickListener {

    private var binding: RegistrationBinding? = null
    private val createUserViewModel: AuthenticationViewModel by viewModel()
    private var loadContent:LoadContentAuthentication?=null
    private var getPreferences: SharedPreferences? = null // хранит в себе данные приложения в независимости от места  где находишься
                                                          // (даже после выхода из прил-я, при возвращении)



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegistrationBinding.inflate(inflater, container, false)

        getPreferences = context?.getSharedPreferences( // инициализация SharedPreferences
            "User", // ключ SharedPreferences к ячейке памяти
            Context.MODE_PRIVATE
        )

        binding?.registration?.setOnClickListener(this)
        binding?.authorization?.setOnClickListener(this)

        return binding?.root

    }

    override fun onClick(view: View) {
       when(view.id) {
           R.id.authorization -> {

               loadContent?.loadContent("logout_authorization") //тег в память, для дальнейшего изъятия

           }

           R.id.registration -> {

               val startToken = generateToken(7)
               val endToken = generateToken(4)
               val code = startToken + binding?.enterCode?.text?.toString() + endToken //рандом  числа +код+ рандом числа


               if (binding?.enterNameAccount?.text?.toString() != "" && binding?.enterCode?.text?.count()==4) {
                   createUserViewModel.auditUserRegistration(binding?.enterNameAccount?.text?.toString() ?: return, code,
                        binding?.errorMessage, context ?: return, {createUser(code)}, {editUser()}, {loadContent()})

               }

               else {
                   binding?.errorMessage?.text = getString(R.string.error_enter_info)
               }

           }
       }
    }

    private fun createUser(enterCode: String){  //создает запись в БД
        createUserViewModel.createUser(binding?.enterNameAccount?.text?.toString() ?: return, enterCode
            ,"user", context ?: return)

    }

    private fun editUser(){
        val editor = getPreferences?.edit()  // запись в SharedPreferences,который хранит имя аккаунта залогиненого usera
        editor?.putString("key", binding?.enterNameAccount?.text?.toString())
        editor?.apply()
    }

    private fun loadContent(){  // просто загружает контент привязанный к залогиненому userу по тегу  authorization
        loadContent?.loadContent("authorization")
    }


    private fun generateToken(length: Int) : String { //шифрование
        val allowedChars = ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }




    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is LoadContentAuthentication) {
            loadContent = context
        }

    }

    override fun onDetach() {
        super.onDetach()
        loadContent = null  // при закрытии приложения, чтобы избежать утечки памяти, обнулять loadContent
    }

}