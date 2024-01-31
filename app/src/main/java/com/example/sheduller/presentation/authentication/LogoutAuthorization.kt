package com.example.sheduller.presentation.authentication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sheduller.R
import com.example.sheduller.databinding.LogoutAuthorizationBinding
import com.example.sheduller.presentation.ScreenApp

import org.koin.androidx.viewmodel.ext.android.viewModel

//авторизация имя/пароль в случае разлогирования
class LogoutAuthorization : Fragment(),View.OnClickListener {

    private var binding: LogoutAuthorizationBinding? = null
    private val userViewModel:AuthenticationViewModel by viewModel()
    private var loadContent:LoadContentAuthentication?=null
    private var getPreferences: SharedPreferences? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LogoutAuthorizationBinding.inflate(inflater, container, false)

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
            R.id.authorization-> {
                if (binding?.enterNameAccount?.text?.toString() != "" && binding?.enterCode?.text?.count()==4){
                    userViewModel.auditUserLogoutAuthorization(binding?.enterNameAccount?.text?.toString() ?: return,
                        binding?.enterCode?.text?.toString() ?: return, binding?.errorMessage ?: return, context ?: return,
                        { editUser() }, {actionAuthorization()}  )
                }

                else {
                    binding?.errorMessage?.text = getString(R.string.error_enter_info)
                }
            }

            R.id.registration-> {

                loadContent?.loadContent("registration")

            }
        }
    }


    private fun editUser() {
        val editor = getPreferences?.edit()
        editor?.putString("key", binding?.enterNameAccount?.text?.toString())
        editor?.apply()
    }

    private fun actionAuthorization() {   // запускает экран ScreenApp
        val startApp = Intent(context, ScreenApp::class.java)
        startActivity(startApp)
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


}