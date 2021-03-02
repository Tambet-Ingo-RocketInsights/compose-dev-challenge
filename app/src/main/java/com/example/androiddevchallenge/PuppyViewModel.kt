package com.example.androiddevchallenge

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.model.Sex
import kotlinx.coroutines.flow.*
import java.time.LocalDate

val puppyList = listOf(
    Puppy(
        name = "Charlie",
        breed = "German Shepherd",
        dateOfBirth = LocalDate.of(2021, 1, 1),
        sex = Sex.Male,
        weight = 10.6f,
        owner = null
    ),
    Puppy(
        name = "Max",
        breed = "Bulldog",
        dateOfBirth = LocalDate.of(2020, 2, 3),
        sex = Sex.Male,
        weight = 10.6f,
        owner = null
    ),
    Puppy(
        name = "Buddy",
        breed = "Poodle",
        dateOfBirth = LocalDate.of(2019, 3, 6),
        sex = Sex.Male,
        weight = 10.6f,
        owner = null
    ),
    Puppy(
        name = "Oscar",
        breed = "Labrador Retriever",
        dateOfBirth = LocalDate.of(2018, 4, 9),
        sex = Sex.Male,
        weight = 10.6f,
        owner = null
    ),
    Puppy(
        name = "Milo",
        dateOfBirth = LocalDate.of(2017, 5, 12),
        breed = "Rottweiler",
        sex = Sex.Male,
        weight = 10.6f,
        owner = null
    ),
    Puppy(
        name = "Bella",
        breed = "Rottweiler",
        dateOfBirth = LocalDate.of(2016, 6, 15),
        sex = Sex.Female,
        weight = 10.6f,
        owner = null
    ),
    Puppy(
        name = "Molly",
        breed = "Labrador Retriever",
        dateOfBirth = LocalDate.of(2015, 7, 18),
        sex = Sex.Female,
        weight = 10.6f,
        owner = null
    ),
    Puppy(
        name = "Coco",
        breed = "Poodle",

        dateOfBirth = LocalDate.of(2014, 8, 21),
        sex = Sex.Female,
        weight = 10.6f,
        owner = null
    ),
    Puppy(
        name = "Ruby",
        breed = "Bulldog",
        dateOfBirth = LocalDate.of(2013, 9, 24),
        sex = Sex.Female,
        weight = 10.6f,
        owner = null
    ),
    Puppy(
        name = "Lucy",
        breed = "German Shepherd",
        dateOfBirth = LocalDate.of(2012, 10, 27),
        sex = Sex.Female,
        weight = 10.6f,
        owner = null
    )
)

class PuppyViewModel : ViewModel() {
    private val _title = MutableStateFlow<String>("Puppies")
    val title: StateFlow<String>
        get() = _title.asStateFlow()

    fun currentTitle(title: String) {
        _title.value = title
    }

    private val _puppies = MutableStateFlow<List<Puppy>>(puppyList.shuffled())
    val puppies: StateFlow<List<Puppy>>
        get() = _puppies.asStateFlow()

    fun puppy(id: String): Flow<Puppy?> {
        return puppies.map { list ->
            list.firstOrNull { puppy -> puppy.id == id }
        }.distinctUntilChanged()
    }
}