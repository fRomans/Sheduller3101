package com.example.sheduller.data.repository.dataSourceImpl

import android.content.Context
import android.content.Intent
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.sheduller.R
import com.example.sheduller.data.api.ApiClient
import com.example.sheduller.data.models.UserApiModel
import com.example.sheduller.data.repository.dataSource.AuthenticationApiDataSource
import com.example.sheduller.presentation.ScreenApp
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthenticationApiDataSourceImpl: AuthenticationApiDataSource {


    //создание пользователя
    override fun createUser(nameAccount: String?, pinCode:String?, role:String?, context: Context) {
        val call: Call<ResponseBody?>? = ApiClient.instance?.api?.createUser(nameAccount, pinCode, role)

        call?.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                Toast.makeText(context, "Пользователь Создан", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Toast.makeText(context, "ВКЛЮЧИТЕ ИНТЕРНЕТ", Toast.LENGTH_SHORT).show()
            }
        })
    }


//регистрация
    override fun auditUserRegistration (nameAccount: String, enterCode:String, textError: TextView?,
                                        context: Context, createUser: (String) -> Unit,
                                        editUser: () -> Unit, loadContent: () -> Unit) {


        val call = ApiClient.instance?.api?.infoUser(nameAccount)

        call?.enqueue(object: Callback<ArrayList<UserApiModel>> {
            override fun onResponse(
                call: Call<ArrayList<UserApiModel>>,
                response: Response<ArrayList<UserApiModel>>
            ) {

                Toast.makeText(context, "ПРОВЕРКА", Toast.LENGTH_SHORT).show()

                val loadInfoUser = response.body()

                if (loadInfoUser?.count()!=0) {
                    textError?.text = (context as FragmentActivity).getString(R.string.error_created_user)
                }

                else {

                    createUser(enterCode)

                    editUser()

                    loadContent()

                }

            }

            override fun onFailure(call: Call<ArrayList<UserApiModel>>, t: Throwable) {
                Toast.makeText(context, "ОШИБКА! ВКЛЮЧИТЕ ИНТЕРНЕТ!", Toast.LENGTH_SHORT).show()
            }
        })

    }

// авторизация
    override fun auditUserAuthorization (nameAccount: String, enterCode:String, textError: TextView?, context: Context) {

        val call = ApiClient.instance?.api?.infoUser(nameAccount)
        call?.enqueue(object : Callback<ArrayList<UserApiModel>> {
            override fun onResponse(
                call: Call<ArrayList<UserApiModel>>,
                response: Response<ArrayList<UserApiModel>>
            ) {

                Toast.makeText(context, "Проверка", Toast.LENGTH_SHORT).show()

                val loadInfoUser = response.body()

                if (enterCode == auditToken(loadInfoUser?.last()?.pinCode.toString())) {

                    val startApp = Intent(context, ScreenApp::class.java)
                    (context as FragmentActivity).startActivity(startApp)

                } else {

                    textError?.text = (context as FragmentActivity).getString(R.string.error_code)

                }

            }

            override fun onFailure(call: Call<ArrayList<UserApiModel>>, t: Throwable) {
                Toast.makeText(context, "ОШИБКА! ВКЛЮЧИТЕ ИНТЕРНЕТ!", Toast.LENGTH_SHORT).show()

            }
        })

    }
//если вышел из акаунта и зашел на экран авторизации
    override fun auditUserLogoutAuthorization (nameAccount: String, enterCode:String, error: TextView,
                                               context: Context, editUser: () -> Unit,
                                               actionAuthorization: () -> Unit) {

        val call = ApiClient.instance?.api?.infoUser(nameAccount)
        call?.enqueue(object: Callback<ArrayList<UserApiModel>> {
            override fun onResponse(
                call: Call<ArrayList<UserApiModel>>,
                response: Response<ArrayList<UserApiModel>>
            ) {

                val loadInfoUser = response.body()

                if (loadInfoUser?.count()==0) {
                    error.text = (context as FragmentActivity).getString(R.string.error_not_user)
                }

                else {

                    actionsUserLogoutAuthorization(error, enterCode, loadInfoUser, context, editUser, actionAuthorization)

                }

                Toast.makeText(context, "Проверка", Toast.LENGTH_SHORT).show()

            }

            override fun onFailure(call: Call<ArrayList<UserApiModel>>, t: Throwable) {
                Toast.makeText(context, "ОШИБКА! ВКЛЮЧИТЕ ИНТЕРНЕТ!", Toast.LENGTH_SHORT).show()

            }
        })

    }

    fun auditToken(code:String):String{
        val audit = code.drop(7)
        return audit.dropLast(4)
    }

    private fun actionsUserLogoutAuthorization(textError: TextView?, enterCode:String,
                                               loadInfoUser:ArrayList<UserApiModel>?, context: Context,
                                               editUser: () -> Unit,
                                               actionAuthorization: () -> Unit){
        if (textError?.text != ""){
            textError?.text = ""
        }

        if (enterCode == auditToken(loadInfoUser?.last()?.pinCode.toString())) {

            editUser()

            actionAuthorization()

        } else {

            textError?.text = (context as FragmentActivity).getString(R.string.error_code)

        }
    }


}