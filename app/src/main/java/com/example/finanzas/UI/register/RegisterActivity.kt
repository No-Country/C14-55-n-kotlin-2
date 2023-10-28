package com.example.finanzas.UI.register

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finanzas.R
import com.example.finanzas.R.id.textViewOlvideMiContrasena
import com.example.finanzas.UI.forgotPassword.ForgotPasswordActivity
import com.example.finanzas.UI.home.MainActivity
import com.example.finanzas.UI.login.LoginActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth



class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize Firebase Auth
        auth = Firebase.auth

        //cambiar interfaz a olvide mi contraseña
        val textViewOlvideMiContrasena: TextView = findViewById(textViewOlvideMiContrasena)
        textViewOlvideMiContrasena.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        //cambiar interfaz a iniciar sesión
        val tvIniciarSesion: TextView = findViewById(R.id.tvIniciarSesion)
        tvIniciarSesion.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        //Evento al hacer click en el boton
        val registerButton: Button = findViewById(R.id.btnRegistrarme)
        registerButton.setOnClickListener {
            perfomerSignUp()
        }
    }


    private fun perfomerSignUp() {
        // Registro con Firebase
        val etNombre = findViewById<TextInputEditText>(R.id.etNombre)
        val email = findViewById<TextInputEditText>(R.id.etCorreoElectronico)
        val contrasena = findViewById<TextInputEditText>(R.id.etContrasena)

        val inputNombre = etNombre.text.toString()
        val inputEmail = email.text.toString()
        val inputContrasena = contrasena.text.toString()

        auth.createUserWithEmailAndPassword(inputEmail, inputContrasena)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val intent = Intent(this, RegisterActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(
                        baseContext, "Registro exitoso.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }
}
