package com.example.afishaapp.domain.valid

class ValidPassword {
    fun execute(password: String): Boolean {
        return password.length >= 6
    }
}