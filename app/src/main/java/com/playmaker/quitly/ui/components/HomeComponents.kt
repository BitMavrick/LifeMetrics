package com.playmaker.quitly.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.playmaker.quitly.ui.theme.CustomTypography
import com.playmaker.quitly.ui.theme.QuitlyTheme
import com.playmaker.quitly.ui.utils.DetailType

@Composable
fun TimeCounter(){
    Card{
        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Outlined.Timer,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null,
                )
                Text(
                    text = "Timer",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .aspectRatio(1f)
            ){
                Surface(
                    Modifier
                        .fillMaxSize()
                ){
                    Column(
                        Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.surfaceVariant),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = "0 DAYS",
                            style = MaterialTheme.typography.displaySmall,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(bottom = 15.dp)
                        )
                        Text(
                            text = "00 : 00 : 00",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    CircularProgressIndicator(
                        progress = 1f,
                        strokeWidth = 10.dp,
                        color = MaterialTheme.colorScheme.inversePrimary
                    )
                    CircularProgressIndicator(
                        progress = 0.4f,
                        strokeWidth = 10.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OutlinedButton(
                    onClick = { },
                    contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
                ) {
                    Icon(
                        Icons.Filled.Delete,
                        contentDescription = "Clear data icon",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Clear Data")
                }

                Button(
                    onClick = { },
                    contentPadding = ButtonDefaults.ButtonWithIconContentPadding
                ) {
                    Icon(
                        Icons.Filled.Refresh,
                        contentDescription = "Gave up icon",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Gave Up")
                }
            }

            Row(Modifier.padding(38.dp)) {
                Text(
                    text = "- Believe in yourself, and you are halfway there!",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressHistory(
    onDetailPress: ((DetailType) -> Unit)
){
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
        ),
        onClick = { onDetailPress(DetailType.HOME_DETAIL) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(60.dp)
                    .aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.inversePrimary)

            ) {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Filled.ShowChart,
                        contentDescription = null,
                    )
                }
            }

            Column(
                Modifier
                    .height(60.dp)
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Progress History",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Tap to see your progress details",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RankCard(
    onDetailPress: ((DetailType) -> Unit)
){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
        ),
        onClick = { onDetailPress(DetailType.RANK_DETAIL) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    Icons.Outlined.Lightbulb,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null,
                )
                Text(
                    text = "Rank Card",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "- This is the rank card",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
fun OverviewCard(){
    Card(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ){
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Corporal",
                style = CustomTypography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Reached 5 Days",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Divider(
                thickness = 3.dp,
                color = MaterialTheme.colorScheme.inversePrimary,
                modifier = Modifier.padding(vertical = 6.dp)
            )

            Row{
                Text(
                    text = "Current Best: ",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 3.dp)
                )

                Text(
                    text = "New",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }

            Row{
                Text(
                    text = "Total Give ups: ",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "3 Times",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
        }
    }
}

@Preview
@Composable
fun ProgressCard(){

    Card(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ){
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Progress",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
            )

            Row(
                Modifier.fillMaxWidth().padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "Upcoming: Nothing",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

/*
                Text(
                    text = buildString {
                        if (days != "0") {
                            append("${days}d ")
                        }
                        append("$hours : $minutes : $seconds")
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

 */

            }

            LinearProgressIndicator(
                progress = 0.4f,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp),
                trackColor = MaterialTheme.colorScheme.inversePrimary,
            )

            Row(
                Modifier.fillMaxWidth().padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Current Progress",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Upcoming progress",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TimeCounterPreview(){
    QuitlyTheme {
        TimeCounter()
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressHistoryPreview(){
    QuitlyTheme {
        ProgressHistory(onDetailPress = { })
    }
}