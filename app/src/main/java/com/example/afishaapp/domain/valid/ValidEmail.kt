package com.example.afishaapp.domain.valid

import java.util.regex.Pattern

class ValidEmail {
    fun execute(email: String): Boolean {
        val regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return Pattern.compile(regexPattern)
            .matcher(email)
            .matches()
    }
}