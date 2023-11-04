package com.example.finanzas.UI.profile.EditProfile

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.finanzas.R
import com.example.finanzas.UI.home.MainActivity
import com.example.finanzas.UI.profile.ProfileFragment
import com.example.finanzas.databinding.ActivityEditProfileBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.auth.userProfileChangeRequest

class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = Firebase.auth.currentUser
        user?.let {
            // Name, email address, and profile photo Url
            val email = it.email
            val name = it.displayName

            // Establece el correo del usuario
            binding.etCorreoElectronico.setText(email)
            binding.etCorreoElectronico.isEnabled = true

            // Establece el texto "Carla" y deshabilita la entrada de texto
            binding.etNombre.setText(name)
            binding.etNombre.isEnabled = true

            SaveChanges()
            Back()


        }
    }

    private fun Back() {
        binding.ivBack.setOnClickListener {
            val intent = Intent(this, ProfileFragment::class.java)
            startActivity(intent)
        }
    }

    private fun SaveChanges() {
        binding.btnSaveChanges.setOnClickListener {
            val name = binding.etNombre.text.toString()
            val email = binding.etCorreoElectronico.text.toString()
            val password = binding.etContrasena.text.toString()
            val comfirmPassword = binding.etConfirmarContrasena.text.toString()

            val user = Firebase.auth.currentUser
            /*
            // Actualiza el correo electrónico
            user!!.updateEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "User email address updated.")
                    }
                }

            // Actualiza el nombre
            if (
            val profileUpdates = userProfileChangeRequest {
                displayName = name
            }
            user!!.updateProfile(profileUpdates)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "User profile updated.")
                    }
                }

            // Actualiza la contraseña
            if (password == comfirmPassword && password.isNotEmpty()) {
                val newPassword = password

                user!!.updatePassword(newPassword)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "User password updated.")
                        }
                    }
            }

            val intent = Intent(this, ProfileFragment::class.java)
            startActivity(intent)

            Toast.makeText(
                baseContext, "Actualizacion Exitosa",
                Toast.LENGTH_SHORT
            ).show()
        */


            // Actualiza el correo electrónico
            user!!.updateEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "User email address updated.")

                        // Actualiza el nombre
                        val profileUpdates = userProfileChangeRequest {
                            displayName = name
                        }
                        user!!.updateProfile(profileUpdates)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Log.d(TAG, "User profile updated.")

                                    // Actualiza la contraseña
                                    if (password == comfirmPassword && password.isNotEmpty()) {
                                        val newPassword = password

                                        user!!.updatePassword(newPassword)
                                            .addOnCompleteListener { task ->
                                                if (task.isSuccessful) {
                                                    Log.d(TAG, "User password updated.")
                                                }
                                            }
                                    }
                                    // Luego de todas las actualizaciones, muestra el Toast y lanza la actividad
                                    showUpdateSuccessToast()
                                    val intent = Intent(this, MainActivity::class.java)
                                    startActivity(intent)
                                }

                            }
                    }
                }
        }
        }

    private fun showUpdateSuccessToast() {
        Toast.makeText(this, "Actualización Exitosa", Toast.LENGTH_SHORT).show()
    }

}
