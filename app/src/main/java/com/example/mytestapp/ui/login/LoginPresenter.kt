package com.example.mytestapp.ui.login

import android.text.TextUtils
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth


class LoginPresenter {

    private lateinit var loginView: LoginView
    var mAuth: FirebaseAuth? = null

    fun attachView(loginView: LoginView, mAuth: FirebaseAuth) {
        this.loginView = loginView
        this.mAuth = mAuth

    }

    fun loginUser(email: String, password: String) {
        when {
            TextUtils.isEmpty(email) -> {
                loginView.showEmptyField()
            }
            TextUtils.isEmpty(password) -> {
                loginView.showEmptyField()
            }
            else -> {
                mAuth?.signInWithEmailAndPassword(email, password)!!
                    .addOnCompleteListener(OnCompleteListener<AuthResult?> { task ->
                        if (task.isSuccessful) {
                            loginView.showSuccess()
                        } else {
                            loginView.showError()
                        }
                    })
            }
        }
    }

}