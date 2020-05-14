package com.example.farmapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.calculator.scientific.calculatrice.extension.toast
import com.example.farmapplication.helper.NetworkConnection

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val networkConnection = NetworkConnection(applicationContext)
        networkConnection.observe(this, Observer { isConnected ->

            if (!isConnected) {
                toast("Please connect internet !!!")
            }

        })

    }

    override fun onDestroy() {
        super.onDestroy()

    }
}
