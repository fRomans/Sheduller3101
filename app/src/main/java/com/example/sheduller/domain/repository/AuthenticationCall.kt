package com.example.sheduller.domain.repository

import android.content.Context
import android.widget.TextView

interface AuthenticationCall {
    fun createUser(nameAccount:String, pinCode:String, role:String, context: Context)
    fun auditUserAuthorization(nameAccount: String, enterCode:String, textError: TextView?, context: Context)
    fun auditUserLogoutAuthorization(nameAccount: String, enterCode:String, error: TextView,
                                     context: Context, editUser: () -> Unit,
                                     actionAuthorization: () -> Unit)
    fun auditUserRegistration(nameAccount: String, enterCode:String, textError: TextView?,
                              context: Context, createUser: (String) -> Unit,
                              editUser: () -> Unit, loadContent: () -> Unit)
}