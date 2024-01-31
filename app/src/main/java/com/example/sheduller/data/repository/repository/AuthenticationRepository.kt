package com.example.sheduller.data.repository.repository

import android.content.Context
import android.widget.TextView
import com.example.sheduller.data.repository.dataSource.AuthenticationApiDataSource
import com.example.sheduller.domain.repository.AuthenticationCall

class AuthenticationRepository(private val apiDataSource: AuthenticationApiDataSource):
    AuthenticationCall {

    override fun createUser(nameAccount:String, pinCode:String, role:String, context: Context) {
        apiDataSource.createUser(nameAccount, pinCode, role, context)
    }

    override fun auditUserAuthorization(nameAccount: String, enterCode:String, textError: TextView?, context: Context) {
        apiDataSource.auditUserAuthorization(nameAccount, enterCode, textError, context)
    }

    override fun auditUserLogoutAuthorization(nameAccount: String, enterCode:String, error: TextView,
                                              context: Context, editUser: () -> Unit,
                                              actionAuthorization: () -> Unit) {
        apiDataSource.auditUserLogoutAuthorization(nameAccount, enterCode, error, context, editUser,
            actionAuthorization)
    }

    override fun auditUserRegistration(nameAccount: String, enterCode:String, textError: TextView?,
                                       context: Context, createUser: (String) -> Unit,
                                       editUser: () -> Unit, loadContent: () -> Unit) {
        apiDataSource.auditUserRegistration(nameAccount, enterCode, textError, context, createUser,
            editUser, loadContent)
    }
}