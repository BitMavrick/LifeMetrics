package com.playmakers.lifemetrics.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.LineType
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import com.playmakers.lifemetrics.data.progressValue.Progress
import com.playmakers.lifemetrics.ui.screens.states.StatesUiState

@Composable
fun OverviewCard(
    currentBest: String,
    days: Int,
    giveUps: Int,
    statesUiState: StatesUiState,
    runningTime: Long
){
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
            if(giveUps == 0){
                Text(
                    text = currentBest,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Reached $days Days",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }else{
                Text(
                    text = "Corporal",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Reached 5 Days",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

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
                    text = currentBest,
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
                    text = "$giveUps Times",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
        }
    }
}

@Composable
fun GraphCard(
    statesUiState: StatesUiState,
    runningTime: Long
){
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
                text = "Struggle Graph",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Card {
                // Dummy Data for the chart
                val steps = (statesUiState.valueList.size + 1)

                val pointsData = listOf(Point(0f, 0f)) + statesUiState.valueList.mapIndexed { index, element ->
                    val serial = index.toFloat() + 1f
                    val point = element.period.toFloat()
                    Point(serial, point)
                } + Point(steps.toFloat(), runningTime.toFloat())

                val xAxisData = AxisData.Builder()
                    .axisStepSize(70.dp)
                    .backgroundColor(Color.Transparent)
                    .steps(pointsData.size - 1)
                    .labelData { i -> i.toString() }
                    .labelAndAxisLinePadding(15.dp)
                    .axisLineColor(MaterialTheme.colorScheme.tertiary)
                    .axisLabelColor(MaterialTheme.colorScheme.tertiary)
                    .build()

                val yAxisData = AxisData.Builder()
                    .steps(steps)
                    .backgroundColor(Color.Transparent)
                    .labelAndAxisLinePadding(20.dp)
                    .axisLineColor(MaterialTheme.colorScheme.tertiary)
                    .axisLabelColor(MaterialTheme.colorScheme.tertiary)
                    .build()

                val lineCharData = LineChartData(
                    linePlotData = LinePlotData(
                        lines = listOf(
                            Line(
                                dataPoints = pointsData,
                                LineStyle(
                                    color = MaterialTheme.colorScheme.tertiary,
                                    lineType = LineType.SmoothCurve(isDotted = false)
                                ),
                                IntersectionPoint(
                                    color = MaterialTheme.colorScheme.tertiary
                                ),
                                SelectionHighlightPoint(color = MaterialTheme.colorScheme.primary),
                                ShadowUnderLine(
                                    alpha = 0.5f,
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            MaterialTheme.colorScheme.inversePrimary,
                                            Color.Transparent
                                        )
                                    )
                                ),
                                SelectionHighlightPopUp()
                            )
                        ),
                    ),
                    backgroundColor = MaterialTheme.colorScheme.surface,
                    xAxisData = xAxisData,
                    yAxisData = yAxisData,
                    gridLines = GridLines(color = MaterialTheme.colorScheme.outlineVariant)
                )

                LineChart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    lineChartData = lineCharData
                )
            }
        }
    }
}

@Composable
fun AchievementCard(
    runningTime: Long,
    progress: Progress
){
    val remainingTime = progress.endTime - runningTime

    val secondsInADay = 24 * 60 * 60
    val days = (remainingTime / secondsInADay).toString()
    val seconds = (remainingTime % 60).toString().padStart(2, '0')
    val minutes = ((remainingTime % 3600) / 60).toString().padStart(2, '0')
    val hours = ((remainingTime % 86400) / 3600).toString().padStart(2, '0')


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
                    text = "Upcoming: ${progress.upcoming}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

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

            }

            LinearProgressIndicator(
                progress = ((runningTime.toFloat() - progress.startTime.toFloat())/(progress.endTime.toFloat() - progress.startTime.toFloat())),
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
                    text = progress.title,
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = progress.upcoming,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}