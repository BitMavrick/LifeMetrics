package com.playmakers.lifemetrics.ui.screens.states

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.playmakers.lifemetrics.ui.AppViewModelProvider
import com.playmakers.lifemetrics.ui.composables.AchievementCard
import com.playmakers.lifemetrics.ui.composables.GraphCard
import com.playmakers.lifemetrics.ui.composables.OverviewCard
import com.playmakers.lifemetrics.ui.screens.home.HomeViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun StatesScreen(
    paddingValues: PaddingValues,
    homeViewModel: HomeViewModel,
    statesViewModel: StatesViewModel = viewModel(
        factory = AppViewModelProvider.Factory
    )
){
    val statesUiState by statesViewModel.statesUiState.collectAsState()
    val mainUiState by homeViewModel.uiState.collectAsState()

    if(mainUiState.runningTimeSeconds != 0L){
        LazyColumn(
            modifier = Modifier
                .consumeWindowInsets(paddingValues)
                .padding(16.dp),
            contentPadding = paddingValues
        ){

            item{
                OverviewCard(
                    homeViewModel.statesProgressCard().title,
                    homeViewModel.statesProgressCard().days,
                    statesUiState.valueList.size,
                    statesUiState,
                    mainUiState.runningTimeSeconds,
                    homeViewModel
                )
            }

            if(statesUiState.valueList.isNotEmpty()){
                item{
                    GraphCard(
                        statesUiState,
                        mainUiState.runningTimeSeconds
                    )
                }
            }

            item{
                AchievementCard(
                    mainUiState.runningTimeSeconds,
                    homeViewModel.statesProgressCard()
                )
            }
        }
    }else{
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Statistics are unavailable for now.\nPlease start the timer.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )

        }

    }
}