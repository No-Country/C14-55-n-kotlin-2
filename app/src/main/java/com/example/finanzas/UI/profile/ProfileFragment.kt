package com.example.finanzas.UI.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.finanzas.UI.login.LoginActivity
import com.example.finanzas.UI.profile.EditProfile.EditProfileActivity
import com.example.finanzas.databinding.FragmentProfileBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth



class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        // Recupera la información del usuario y actualiza las vistas
        val user = Firebase.auth.currentUser
        user?.let {
            // Name, email address, and profile photo Url
            val email = it.email
            val name = it.displayName

            // Establece el correo del usuario
            binding.etCorreoElectronico.setText(email)
            binding.etCorreoElectronico.isEnabled = false

            // Establece el texto "Carla" y deshabilita la entrada de texto
            binding.etNombre.setText(name)
            binding.etNombre.isEnabled = false
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


            binding.btnCerrarSesion.setOnClickListener {
                Firebase.auth.signOut()
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
            }

            binding.btnEdit.setOnClickListener {
                val intent = Intent(requireContext(), EditProfileActivity::class.java)
                startActivity(intent)
            }

        }


    }


