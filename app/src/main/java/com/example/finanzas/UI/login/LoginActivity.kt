package com.example.finanzas.UI.login

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.finanzas.R
import com.example.finanzas.UI.forgotPassword.ForgotPasswordActivity
import com.example.finanzas.UI.home.MainActivity
import com.example.finanzas.UI.login.viewModel.LoginViewModel
import com.example.finanzas.UI.register.RegisterActivity
import com.example.finanzas.UI.summary.viewModel.SummaryViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val loginViewModel: LoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initUI()

    }

    private fun initUI() {
        initAuth()
        initViewModel()
    }

    private fun initViewModel() {
        val sharedPreferences = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        if (!sharedPreferences.getBoolean("FirstRun", false)) {
            // Este bloque de código se ejecutará solo la primera vez que se abra la aplicación

            lifecycleScope.launch {
                val getCategoriesJob = async {  loginViewModel.insertTypeCategories() }
                getCategoriesJob.await()
                val getMovementsJob = async {  loginViewModel.insertCategories() }
                getMovementsJob.await()
            }

            // Actualiza las preferencias compartidas para indicar que la aplicación ya se ha abierto antes
            sharedPreferences.edit().putBoolean("FirstRun", true).apply()
        }
    }

    private fun initAuth() {
        // Initialize Firebase Auth
        auth = Firebase.auth

        //cambiar interfaz a olvide mi contraseña
        val textViewOlvideMiContrasena: TextView = findViewById(R.id.textViewOlvideMiContrasena)
        textViewOlvideMiContrasena.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        //cambiar interfaz a registro
        val tvRegistrarme: TextView = findViewById(R.id.tvRegistrarme)
        tvRegistrarme.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        //Evento al hacer click en el boton
        val loginButton: Button = findViewById(R.id.btnIniciarSesion)
        loginButton.setOnClickListener {
            perfomerSignIn()
        }
    }


    private fun perfomerSignIn() {
            // Registro con Firebase
            val email = findViewById<TextInputEditText>(R.id.etCorreoElectronico)
            val contrasena = findViewById<TextInputEditText>(R.id.etContrasena)

            val inputEmail = email.text.toString()
            val inputContrasena = contrasena.text.toString()

            auth.signInWithEmailAndPassword(inputEmail, inputContrasena)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)

                        Toast.makeText(
                            baseContext, "Inicio exitoso.",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }

        }





    }
