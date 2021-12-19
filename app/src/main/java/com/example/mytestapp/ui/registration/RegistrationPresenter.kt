package com.example.mytestapp.ui.registration

import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast

import android.content.Intent

import androidx.core.content.ContextCompat.startActivity

import com.google.firebase.auth.AuthResult

import com.google.android.gms.tasks.OnCompleteListener

import android.text.TextUtils
import androidx.core.content.ContextCompat
import com.google.firebase.database.DatabaseReference


class RegistrationPresenter {

    var mAuth: FirebaseAuth? = null
    lateinit var registrationView : RegistrationView
    private var mDatabase: DatabaseReference? = null

    fun attachView(registrationView: RegistrationView, mAuth : FirebaseAuth, mDatabaseReference: DatabaseReference){
        this.registrationView = registrationView
        this.mAuth = mAuth
        mDatabase = mDatabaseReference
    }

    fun createUser(email: String,  password: String, confirmPass: String) {

        when {
            TextUtils.isEmpty(email) -> {
                registrationView.showEmptyField()
            }
            TextUtils.isEmpty(password) -> {
                registrationView.showEmptyField()
            }
            else -> {
                if (confirmPass == password){
                mAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        registrationView.showSuccess()
                        mAuth!!.uid?.let { mDatabase?.child("users")?.child(it)?.setValue(email) };
                    } else {
                        registrationView.showError()
                    }
                }
                }else{
                    registrationView.showNoMatch()
                }
            }
        }
    }

}
