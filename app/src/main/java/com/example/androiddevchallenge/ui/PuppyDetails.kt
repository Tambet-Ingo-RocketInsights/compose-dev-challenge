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
package com.example.androiddevchallenge.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.model.Sex
import com.example.androiddevchallenge.puppyList
import com.example.androiddevchallenge.ui.theme.MyTheme

private fun createAnnotatedString(label: String, value: String): AnnotatedString =
    with(AnnotatedString.Builder()) {
        pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
        append(label)
        append(':')
        pop()
        append(' ')
        append(value)
        toAnnotatedString()
    }

private fun Sex.format(): String = when (this) {
    Sex.Male -> "Boy"
    Sex.Female -> "Girl"
}

@Composable
fun PuppyDetails(puppy: Puppy) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Text(text = createAnnotatedString("Name", puppy.name))
        Text(text = createAnnotatedString("Breed", puppy.breed))
        Text(
            text = createAnnotatedString(
                "Age",
                stringResource(id = R.string.age, puppy.age)
            )
        )
        Text(text = createAnnotatedString("Sex", puppy.sex.format()))

        puppy.owner?.let {
            Text(text = createAnnotatedString("Owner", it))
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun PuppyDetailsPreview() {
    MyTheme {
        Surface(color = MaterialTheme.colors.background) {
            PuppyDetails(puppyList.first())
        }
    }
}
