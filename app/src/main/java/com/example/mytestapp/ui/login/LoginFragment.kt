package com.example.mytestapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.mytestapp.R
import com.example.mytestapp.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment(), LoginView {

    lateinit var binding: FragmentLoginBinding
    lateinit var mAuth: FirebaseAuth
    private val presenter: LoginPresenter = LoginPresenter()
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mAuth = FirebaseAuth.getInstance()
        presenter.attachView(this, mAuth)
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.apply {
            registrationBtn.setOnClickListener {
                presenter.loginUser(emailEt.text.toString(), passwordEt.text.toString())
                registrationBtn.isEnabled = false
            }
            accountTxt.setOnClickListener {
                navController.navigate(R.id.action_loginFragment_to_registrationFragment)
            }
        }
    }

    override fun showError() {
        Toast.makeText(requireContext(), "Error in firebase", Toast.LENGTH_SHORT).show()
        binding.registrationBtn.isEnabled = true
    }

    override fun showEmptyField() {
        Toast.makeText(requireContext(), "Please fill the blanks", Toast.LENGTH_SHORT).show()
        binding.registrationBtn.isEnabled = true
    }

    override fun showSuccess() {
        navController.navigate(R.id.action_loginFragment_to_homeFragment)
        binding.registrationBtn.isEnabled = true
    }

}