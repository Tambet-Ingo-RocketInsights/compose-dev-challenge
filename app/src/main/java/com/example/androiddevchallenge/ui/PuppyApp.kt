package com.example.androiddevchallenge.ui

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.androiddevchallenge.PuppyViewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Puppy

@Composable
fun PuppyApp(viewModel: PuppyViewModel = viewModel()) {
    val navController = rememberNavController()

    Scaffold(topBar = {
        val title by viewModel.title.collectAsState()
        TopAppBar(title = {
            Text(text = title)
        })
    }) {
        Surface(color = MaterialTheme.colors.background) {
            NavHost(navController = navController, startDestination = NavDestinations.PuppyList) {
                composable(NavDestinations.PuppyList) {
                    viewModel.currentTitle(stringResource(id = R.string.app_name))
                    val puppies: List<Puppy> by viewModel.puppies.collectAsState(emptyList())
                    PuppyList(puppies) {
                        navController.navigate("${NavDestinations.PuppyDetails}/${it.id}")
                    }
                }

                composable("${NavDestinations.PuppyDetails}/{puppyId}",
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
