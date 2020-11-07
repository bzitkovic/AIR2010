package com.example.circuitmessing

import android.content.Intent
import android.os.Bundle
import androidx.navigation.ui.AppBarConfiguration
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.escaper.escaper.utils.preferences
import com.example.circuitmessing.databinding.ActivityLoginBinding
import com.example.circuitmessing.databinding.FragmentLoginFragmentBinding
import com.example.circuitmessing.databinding.FragmentRegisterFragmentBinding
import com.example.circuitmessing.fragment_login
import com.example.circuitmessing.fragment_register
import com.example.circuitmessing.ui.classes.User
import com.google.android.material.button.MaterialButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.view.*

class LoginActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var loginBindingView: ActivityLoginBinding
    private var mFragmentManager: FragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //val toolbar: Toolbar = findViewById(R.id.toolbar)
        //setSupportActionBar(toolbar)


        //If the session is keep to avoid the need to reconnect user each launch
        if (preferences.isConnected){
            //A way to change activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        // Bind the LoginView and show it
        loginBindingView = ActivityLoginBinding.inflate(layoutInflater)
        val loginView = loginBindingView.root
        setContentView(loginView)

        val fragmentTransaction : FragmentTransaction = mFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.login_and_register_fragment,
            fragment_login()
        )
        fragmentTransaction.commit()

        val signUpButton = loginBindingView.signUpButton
        val signInButton = loginBindingView.signInButton


        signUpButton.setOnClickListener(){
            showRegisterFragment()
        }

        signInButton.setOnClickListener {
            showLoginFragment()
        }

    }

    private fun showRegisterFragment(){
        val fragmentTransaction : FragmentTransaction = mFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.login_and_register_fragment,
            fragment_register()
        )
        fragmentTransaction.commit()
    }

    private fun showLoginFragment(){
        val fragmentTransaction : FragmentTransaction = mFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.login_and_register_fragment,
            fragment_login()
        )
        fragmentTransaction.commit()
    }
}

