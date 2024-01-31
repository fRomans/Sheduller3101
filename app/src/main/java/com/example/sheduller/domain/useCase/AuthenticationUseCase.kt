package com.example.sheduller.domain.useCase

import android.content.Context
import android.widget.TextView
import com.example.sheduller.domain.repository.AuthenticationCall

class AuthenticationUseCase(private val call: AuthenticationCall) {

    fun createUser (nameAccount:String, pinCode:String, role:String, context: Context) {

        call.createUser(nameAccount, pinCode, role, context)

    }


    fun auditUserAuthorization (nameAccount: String, enterCode:String, textError: TextView?, context: Context) {

        call.auditUserAuthorization(nameAccount, enterCode, textError, context)

    }

    fun auditUserLogoutAuthorization (nameAccount: String, enterCode:String, error: TextView,
                                      context: Context, editUser: () -> Unit,
                                      actionAuthorization: () -> Unit) {
        call.auditUserLogoutAuthorization(nameAccount, enterCode, error, context, editUser, actionAuthorization)
    }

    fun auditUserRegistration (nameAccount: String, enterCode:String, textError: TextView?,
                               context: Context, createUser: (String) -> Unit,
                               editUser: () -> Unit, loadContent: () -> Unit) {
        call.auditUserRegistration(nameAccount, enterCode, textError, context, createUser, editUser, loadContent)
    }
}