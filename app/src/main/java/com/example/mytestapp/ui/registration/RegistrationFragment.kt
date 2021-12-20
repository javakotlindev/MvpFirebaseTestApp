package com.example.mytestapp.ui.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.mytestapp.R
import com.example.mytestapp.databinding.FragmentRegistrationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.AndroidEntryPoint

class RegistrationFragment : Fragment(), RegistrationView {

    private lateinit var binding: FragmentRegistrationBinding
    lateinit var navController: NavController
    lateinit var mAuth: FirebaseAuth
    private var mDatabase: DatabaseReference? = null

    private val presenter: RegistrationPresenter = RegistrationPresenter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().reference;
        presenter.attachView(this, mAuth, mDatabase!!)
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.apply {
            registrationBtn.setOnClickListener {
                presenter.createUser(
                    emailEt.text.toString(),
                    passwordEt.text.toString(),
                    confirmPasswordEt.text.toString()
                )
                registrationBtn.isEnabled = false
            }
            accountTxt.setOnClickListener {
                navController.navigate(R.id.action_registrationFragment_to_loginFragment)
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
        navController.navigate(R.id.action_registrationFragment_to_homeFragment)
        binding.registrationBtn.isEnabled = true
    }

    override fun showNoMatch() {
        Toast.makeText(
            requireContext(),
            "Your confirm password not equals password",
            Toast.LENGTH_SHORT
        ).show()
        binding.registrationBtn.isEnabled = true
    }
}