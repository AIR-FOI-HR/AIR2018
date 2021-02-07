package com.example.smartwaiter.ui.auth.login

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController

import com.example.smartwaiter.R


import com.example.smartwaiter.util.enable

import com.example.smartwaiter.util.startNewActivity
import com.example.smartwaiter.util.visible
import com.google.common.hash.Hashing

import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch
import java.nio.charset.StandardCharsets

class HomeFragment : Fragment(R.layout.fragment_home) {




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBarLogin.visible(false)
        btnLogin.enable(false)






        btnRegister.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToRegisterFragment()
            findNavController().navigate(action)
        }
    }


}


