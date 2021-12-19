package com.example.mytestapp.ui.registration

import java.util.*

sealed class RegistrationState(
    Success : RegistrationState?,
    Error : RegistrationState,
    EmptyPassword : RegistrationState,
    EmptyEmail: RegistrationState,
    )
