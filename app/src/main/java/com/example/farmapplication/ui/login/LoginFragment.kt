package com.example.farmapplication.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.farmapplication.R
import com.example.farmapplication.data.model.UserEntity
import com.example.farmapplication.data.remote.ApiHelper
import com.example.farmapplication.data.remote.RetrofitBuilder
import com.example.farmapplication.extension.snackbar
import com.example.farmapplication.ui.factory.LoginFactory
import kotlinx.android.synthetic.main.login_fragment.*


class LoginFragment : Fragment() {


    private lateinit var viewModel: LoginViewModel

    private lateinit var navController: NavController
    private var mListUser = mutableListOf<UserEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        userMapList()

        ed_username.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s?.length!! > 0) {
                    tvl_username.error = null
                } else {
                    tvl_username.error = getString(R.string.err_username_validation)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        btn_login.setOnClickListener {

            val user = UserEntity(ed_username.text.toString(), ed_password.text.toString())

            if (user in mListUser) {
                navController.navigate(R.id.action_loginFragment_to_homeFragment)
            }else{
                constraint_login.snackbar("Tài khoản và mật khẩu không chính xác")
            }

        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(this, LoginFactory(ApiHelper(RetrofitBuilder.apiService))).get(
                LoginViewModel::class.java
            )
    }

    private fun userMapList() {
        mListUser.add(UserEntity("haudv@gmail.com", ""))
        mListUser.add(UserEntity("tanhlh520@wru.vn", ""))
        mListUser.add(UserEntity("vinhlc@gmail.com", ""))
        mListUser.add(UserEntity("haiqn@gmail.com", ""))
    }


}
