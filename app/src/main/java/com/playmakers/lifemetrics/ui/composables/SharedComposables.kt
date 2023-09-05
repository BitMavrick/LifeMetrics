package com.playmakers.lifemetrics.ui.composables

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.playmakers.lifemetrics.ui.screens.home.HomeViewModel
import com.playmakers.lifemetrics.ui.theme.LifeMetricsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(){
    TopAppBar(
        title = {
            Text(
                "LifeMetrics",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Localized description"
                )
            }
        },
    )
}

@Composable
fun NavigationBar(
    homeViewModel: HomeViewModel = viewModel()
){
    androidx.compose.material3.NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = null) },
            selected = !homeViewModel.isShowStateScreen.value,
            onClick = { homeViewModel.showHomeScreen() }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.BarChart, contentDescription = null) },
            selected = homeViewModel.isShowStateScreen.value,
            onClick = { homeViewModel.showStateScreen() }
        )
    }
}

@Preview(name = "Top Bar Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TopBarPreview(){
    LifeMetricsTheme {
        TopBar()
    }
}

@Preview(name = "Action Buttons Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun NavigationBarPreview(){
    LifeMetricsTheme {
        NavigationBar()
    }
}