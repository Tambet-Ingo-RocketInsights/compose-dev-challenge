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

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.PuppyViewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Puppy

@Composable
fun PuppyApp(viewModel: PuppyViewModel = viewModel()) {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            val title by viewModel.title.collectAsState()
            val backStackEntry by navController.currentBackStackEntryAsState()

            if (backStackEntry?.destination?.id == backStackEntry?.destination?.parent?.startDestination) {
                TopAppBar(
                    title = {
                        Text(text = title)
                    }
                )
            } else {
                TopAppBar(
                    title = {
                        Text(text = title)
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                        }
                    }
                )
            }
        }
    ) {
        Surface(color = MaterialTheme.colors.background) {
            NavHost(navController = navController, startDestination = NavDestinations.PuppyList) {
                composable(NavDestinations.PuppyList) {
                    viewModel.currentTitle(stringResource(id = R.string.app_name))
                    val puppies: List<Puppy> by viewModel.puppies.collectAsState(emptyList())
                    PuppyList(puppies) {
                        navController.navigate("${NavDestinations.PuppyDetails}/${it.id}")
                    }
                }

                composable(
                    "${NavDestinations.PuppyDetails}/{puppyId}",
                    arguments = listOf(navArgument("puppyId") { type = NavType.StringType })
                ) {
                    val puppyId = it.arguments?.getString("puppyId")!!
                    val puppy by viewModel.puppy(puppyId).collectAsState(null)
                    puppy?.let {
                        viewModel.currentTitle(it.name)
                        PuppyDetails(it)
                    }
                }
            }
        }
    }
}
