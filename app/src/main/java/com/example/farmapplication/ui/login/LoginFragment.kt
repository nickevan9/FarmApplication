package com.example.farmapplication.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.farmapplication.R
import com.example.farmapplication.data.remote.ApiHelper
import com.example.farmapplication.data.remote.RetrofitBuilder
import com.example.farmapplication.ui.factory.LoginFactory
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(this, LoginFactory(ApiHelper(RetrofitBuilder.apiService))).get(
                LoginViewModel::class.java
            )

        ed_username.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.isNotEmpty()) {
                    if (!TextUtils.isEmpty(tvl_username.error)) {
                        tvl_username.error = null
                        tvl_username.isErrorEnabled = false
                    }
                }
            }

        })

    }

}
