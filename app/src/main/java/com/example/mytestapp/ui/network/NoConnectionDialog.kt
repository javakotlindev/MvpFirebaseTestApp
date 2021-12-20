package com.example.mytestapp.ui.network

import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.mytestapp.databinding.DialogNoConnectionBinding

class NoConnectionDialog(context: Context) : AlertDialog(context) {
    private val binding = DialogNoConnectionBinding.inflate(LayoutInflater.from(context))

    init {
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        setCancelable(false)

        binding.checkConnection.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                try {
                    context.startActivity(Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY))
                } catch (e: Exception) {
                }
            } else {
                try {
                    context.startActivity(Intent(Settings.ACTION_DATA_ROAMING_SETTINGS))
                } catch (e: Exception) {
                }
            }
        }

        setView(binding.root)
    }
}