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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.puppyList
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun PuppyList(puppies: List<Puppy>,
              onClick: (Puppy) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
    ) {
        items(puppies) { puppy ->
            PuppyItem(puppy, onClick)
        }
    }
}

@Composable
fun PuppyItem(puppy: Puppy,
              onClick: (Puppy) -> Unit) {
    Row(modifier = Modifier.clickable { onClick.invoke(puppy) }) {
        Text(
            text = puppy.name,
            modifier = Modifier.weight(1f)
        )

        Text(text = puppy.breed)
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun PuppyItemPreview() {
    MyTheme {
        Surface(color = MaterialTheme.colors.background) {
            PuppyItem(puppyList.first()) {
                // On Item clicked
            }
        }
    }
}
