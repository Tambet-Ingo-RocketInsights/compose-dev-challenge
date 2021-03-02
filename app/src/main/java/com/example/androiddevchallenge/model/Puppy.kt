package com.example.androiddevchallenge.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

private fun generateId(): String = UUID.randomUUID().toString()

@Parcelize
data class Puppy(
    val id: String = generateId(),
    val name: String,
    val breed: String,
    val dateOfBirth: LocalDate,
    val sex: Sex,
    val weight: Float,
    val owner: String?
) : Parcelable {
    val age: Int
        get() = ChronoUnit.YEARS.between(dateOfBirth, LocalDate.now()).toInt()
}
