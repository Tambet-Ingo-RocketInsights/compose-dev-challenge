package com.example.androiddevchallenge.model

enum class Sex {
    Male,
    Female;

    override fun toString(): String = when (this) {
        Male -> "M"
        Female -> "F"
    }
}