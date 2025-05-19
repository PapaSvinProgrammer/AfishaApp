package com.example.afishaapp.ui.screen.chart

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.afishaapp.R
import com.example.afishaapp.ui.screen.dialog.ClearHistoryDialog
import com.example.afishaapp.ui.screen.dialog.HistoryResultDialog
import com.example.afishaapp.ui.theme.DefaultPadding
import com.example.afishaapp.ui.widget.material.rememberMarker
import com.example.afishaapp.ui.widget.text.TitleTopBar
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberAxisLabelComponent
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottom
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStart
import com.patrykandpatrick.vico.compose.cartesian.cartesianLayerPadding
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberColumnCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.common.component.rememberLineComponent
import com.patrykandpatrick.vico.compose.common.fill
import com.patrykandpatrick.vico.core.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.core.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.CartesianValueFormatter
import com.patrykandpatrick.vico.core.cartesian.data.columnSeries
import com.patrykandpatrick.vico.core.cartesian.layer.ColumnCartesianLayer
import com.patrykandpatrick.vico.core.cartesian.marker.ColumnCartesianLayerMarkerTarget
import com.patrykandpatrick.vico.core.cartesian.marker.DefaultCartesianMarker
import com.patrykandpatrick.vico.core.common.data.ExtraStore
import kotlin.text.Typography.bullet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChartScreen(
    navController: NavController,
    viewModel: ChartViewModel
) {
    val searchHistoryItemPage = viewModel.searchHistoryResult.collectAsLazyPagingItems()

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.getAllCountFavorite()
        viewModel.getCountSearchHistory()
        viewModel.getCountEventCategory()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTopBar(text = stringResource(R.string.statistics))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            HistorySearchContent(
                count = viewModel.searchHistoryCount,
                onDetail = { viewModel.updateResultDialogState(true) },
                onClear = { viewModel.updateClearDialogState(true) }
            )

            ChartContent(
                title = stringResource(R.string.add_in_favorite),
                data = viewModel.countFavorite
            )

            ChartContent(
                title = stringResource(R.string.event_by_category),
                data = viewModel.eventCategory
            )
        }
    }

    if (viewModel.clearHistoryDialogState) {
        ClearHistoryDialog(
            onConfirm = {
                viewModel.clearSearchHistory()
                viewModel.updateClearDialogState(false)
            },
            onDismiss = { viewModel.updateClearDialogState(false) }
        )
    }

    if (viewModel.historyResultDialogState) {
         HistoryResultDialog(
             historyResult = searchHistoryItemPage,
             onDismiss = {
                 viewModel.updateResultDialogState(false)
             }
         )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChartContent(
    title: String,
    data: Map<String, Int>
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
    ) {
        TitleRow(
            text = title,
            icon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
            onClick = { isExpanded = !isExpanded }
        )

        if (isExpanded) {
            RatiosChart(
                modifier = Modifier.padding(horizontal = 10.dp),
                data = data
            )

            Spacer(modifier = Modifier.height(10.dp))
            ListText(data)
        }
    }
}

@Composable
private fun TitleRow(
    text: String,
    fontWeight: FontWeight = FontWeight.Bold,
    fontSize: TextUnit = 18.sp,
    icon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = null,
                indication = null,
                onClick = onClick
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = text,
            fontWeight = fontWeight,
            fontSize = fontSize,
            modifier = Modifier.padding(DefaultPadding)
        )

        Box(modifier = Modifier.padding(DefaultPadding)) {
            icon()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HistorySearchContent(
    count: Int,
    onDetail: () -> Unit,
    onClear: () -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
    ) {
        TitleRow(
            text = stringResource(R.string.search_history),
            icon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
            onClick = { isExpanded = !isExpanded }
        )

        if (isExpanded) {
            Text(
                text = stringResource(R.string.count_response, count),
                modifier = Modifier.padding(horizontal = DefaultPadding)
            )
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = DefaultPadding),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedButton(
                    border = BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.error
                    ),
                    onClick = onClear,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Text(
                        text = stringResource(R.string.clear),
                        color = MaterialTheme.colorScheme.error
                    )
                }

                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    onClick = onDetail
                ) {
                    Text(
                        text = stringResource(R.string.show),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Composable
private fun ListText(map: Map<String, Int>) {
    val paragraphStyle = ParagraphStyle(textIndent = TextIndent(restLine = 12.sp))

    Text(
        text = buildAnnotatedString {
            map.forEach {
                withStyle(style = paragraphStyle) {
                    append(bullet)
                    append("\t\t")
                    append("${it.key}: ${it.value}")
                }
            }
        },
        modifier = Modifier.padding(horizontal = DefaultPadding)
    )
}

@Composable
private fun RatiosChart(
    modifier: Modifier = Modifier,
    data: Map<String, Int>
) {
    val bottomAxisLabelKey = ExtraStore.Key<List<String>>()

    val startAxisValueFormatter = CartesianValueFormatter { _, value, _ ->
        value.toInt().toString()
    }

    val bottomAxisValueFormatter = CartesianValueFormatter { context, x, _ ->
        context.model.extraStore[bottomAxisLabelKey][x.toInt()]
    }

    val markerValueFormatter = DefaultCartesianMarker.ValueFormatter { _, targets ->
        val column = (targets[0] as ColumnCartesianLayerMarkerTarget).columns[0]

        SpannableStringBuilder().append(
            column.entry.y.toString(),
            ForegroundColorSpan(column.color),
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }

    val modelProducer = remember { CartesianChartModelProducer() }

    LaunchedEffect(Unit) {
        modelProducer.runTransaction {
            columnSeries { series(data.values) }
            extras { it[bottomAxisLabelKey] = data.keys.toList() }
        }
    }

    CartesianChartHost(
        chart = rememberCartesianChart(
            rememberColumnCartesianLayer(
                ColumnCartesianLayer.ColumnProvider.series(
                    rememberLineComponent(
                        fill = fill(MaterialTheme.colorScheme.primary),
                        thickness = 40.dp
                    )
                )
            ),
            startAxis = VerticalAxis.rememberStart(
                valueFormatter = startAxisValueFormatter,
                label = rememberAxisLabelComponent(
                    color = MaterialTheme.colorScheme.onSurface
                )
            ),
            bottomAxis = HorizontalAxis.rememberBottom(
                itemPlacer = remember { HorizontalAxis.ItemPlacer.segmented() },
                valueFormatter = bottomAxisValueFormatter,
                label = rememberAxisLabelComponent(
                    color = MaterialTheme.colorScheme.onSurface
                )
            ),
            marker = rememberMarker(markerValueFormatter),
            layerPadding = { cartesianLayerPadding(scalableStart = 8.dp, scalableEnd = 8.dp) },
        ),
        modelProducer = modelProducer,
        modifier = modifier.height(220.dp)
    )
}