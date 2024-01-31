package com.example.sheduller.presentation.authentication

import android.content.Context
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sheduller.domain.useCase.AuthenticationUseCase
import kotlinx.coroutines.launch

class AuthenticationViewModel (private val useCase: AuthenticationUseCase):
    ViewModel() {

    fun createUser(nameAccount:String, pinCode:String, role:String, context: Context) {
        useCase.createUser(nameAccount, pinCode, role, context)
    }

    fun auditUserAuthorization (nameAccount: String, enterCode:String, textError: TextView?, context: Context) {
        useCase.auditUserAuthorization(nameAccount, enterCode, textError, context)

    }

    fun auditUserLogoutAuthorization (nameAccount: String, enterCode:String, error:TextView,
                                      context: Context, editUser: () -> Unit,
                                      actionAuthorization: () -> Unit) {
        useCase.auditUserLogoutAuthorization(nameAccount, enterCode, error, context, editUser, actionAuthorization)

    }

    fun auditUserRegistration (nameAccount: String, enterCode:String, textError: TextView?,
                               context: Context, createUser: (String) -> Unit,
                               editUser: () -> Unit, loadContent: () -> Unit) {
        useCase.auditUserRegistration(nameAccount, enterCode, textError, context, createUser, editUser, loadContent)

    }




}