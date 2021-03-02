/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.model.Sex
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
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
