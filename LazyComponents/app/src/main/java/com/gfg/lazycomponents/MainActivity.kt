package com.gfg.lazycomponents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gfg.lazycomponents.ui.theme.GreenGfg
import com.gfg.lazycomponents.ui.theme.LazyComponentsTheme

class MainActivity : ComponentActivity() {

    val numbers: Array<Int> = Array(100) { it + 1 }

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyComponentsTheme {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    Column(Modifier.fillMaxHeight(0.5f)) {
                        Text(
                            text = "Row",
                            color = Color.Black,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        RowExample(numbers = numbers)
                        Text(
                            text = "Column",
                            color = Color.Black,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        ColumnExample(numbers = numbers)
                    }
                    Column(Modifier.fillMaxHeight()) {
                        Text(
                            text = "Grid",
                            color = Color.Black,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        GridExample(numbers = numbers)
                    }
                }
            }
        }
    }
}


@Composable
fun RowExample(numbers: Array<Int>) {

    LazyRow(
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        item {
            ExampleCardForRow(number = 0)
        }

        items(10) {
            ExampleCardForRow(number = it)
        }
        items(numbers) {
            ExampleCardForRow(number = it)
        }

        itemsIndexed(numbers) { index: Int, item: Int ->
            ExampleCardForRow(number = index)
        }
    }

}


@Composable
fun ExampleCardForRow(number: Int) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(100.dp)
            .background(Color.White)
            .border(1.dp, GreenGfg)


    ) {

        Text(text = "This Is Item Number $number", color = GreenGfg)
    }

}


@Composable
fun ColumnExample(numbers: Array<Int>) {

    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item { ExampleCardForColumn(number = 0) }
        items(10) {
            ExampleCardForColumn(number = it)
        }
        items(numbers) {
            ExampleCardForColumn(number = it)
        }
        itemsIndexed(numbers) { index, item ->
            ExampleCardForColumn(number = index)
        }
    }
}


@Composable
fun ExampleCardForColumn(number: Int) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .background(Color.White)
            .border(1.dp, GreenGfg)

    ) {

        Text(text = "This Is Item Number $number", color = GreenGfg)
    }

}


@ExperimentalFoundationApi
@Composable
fun GridExample(numbers: Array<Int>) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),

        contentPadding = PaddingValues(8.dp),

        ) {

        item {
            ExampleCardForRow(number = 0)
        }
        items(10) {
            ExampleCardForRow(number = it)
        }
        items(numbers) {
            ExampleCardForRow(number = it)
        }
        itemsIndexed(numbers) { index, item ->
            ExampleCardForRow(number = index)
        }
    }
}