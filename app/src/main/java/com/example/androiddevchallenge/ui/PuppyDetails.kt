package com.example.androiddevchallenge.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.puppyList
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun PuppyDetails(puppy: Puppy) {
    Column() {
        Text(text = "Name: ${puppy.name}")
        Text(text = "Breed: ${puppy.breed}")
        Text(text = "Age: ${puppy.age}")
        Text(text = "Sex: ${puppy.sex}")

        puppy.owner?.let {
            Text(text = "Owner: $it")
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
