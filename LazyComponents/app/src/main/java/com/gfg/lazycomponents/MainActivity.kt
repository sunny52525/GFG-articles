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

    //Creates array as [0,1,2,3,4,5,.....99]
    private val numbers: Array<Int> = Array(100) { it + 1 }

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


                    //Place the row and column to take 50% height of screen
                    Column(Modifier.fillMaxHeight(0.5f)) {

                        //Heading
                        Text(
                            text = "Row",
                            color = Color.Black,
                            modifier = Modifier.padding(start = 8.dp)
                        )

                        //Lazy Row,pass the numbers array
                        LazyRowExample(numbers = numbers)

                        //Heading
                        Text(
                            text = "Column",
                            color = Color.Black,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        //Lazy Column, Pass the numbers array
                        LazyColumnExample(numbers = numbers)
                    }



                    Column(Modifier.fillMaxHeight()) {

                        //Heading
                        Text(
                            text = "Grid",
                            color = Color.Black,
                            modifier = Modifier.padding(start = 8.dp)
                        )

                        //Lazy Grid
                        GridExample(numbers = numbers)
                    }
                }
            }
        }
    }
}


@Composable
fun LazyRowExample(numbers: Array<Int>) {

    //Place A lazy Row
    LazyRow(
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp) //Each Item in LazyRow have a 8.dp margin
    ) {

        //item places one item on the LazyScope
        item {
            RowItem(number = 0)
        }

        //items(count) places number of items supplied as count and gives current count in the lazyItemScope
        items(10) {currentCount->
            RowItem(number = currentCount)
        }

        //items(list/array) places number of items same as the size of list/array and gives current list/array item in the lazyItemScope
        items(numbers) {arrayItem-> //Here numbers is Array<Int> so we get Int in the scope.
            RowItem(number = arrayItem)
        }

        //items(list/array) places number of items same as the size of list/array and gives current list/array item and currentIndex in the lazyItemScope
        itemsIndexed(numbers) { index: Int, item: Int ->
            RowItem(number = index)
        }
    }

}


@Composable
fun RowItem(number: Int) {


    //Simple Row Composable
    Row(
        modifier = Modifier
            .size(100.dp)//Size 100 dp
            .background(Color.White)//Background White
            .border(1.dp, GreenGfg), //Border color green

        //Align Items in Center
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center


    ) {

        //Text Composable which displays some kind of message , text color is green
        Text(text = "This Is Item Number $number", color = GreenGfg)
    }

}


@Composable
fun ColumnItem(number: Int) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .background(Color.White)
            .border(1.dp, GreenGfg),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(text = "This Is Item Number $number", color = GreenGfg)
    }

}


@Composable
fun LazyColumnExample(numbers: Array<Int>) {

    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        //item places one item on the LazyScope

        item {
            ColumnItem(number = 0)
        }

        //items(count) places number of items supplied as count and gives current count in the lazyItemScope
        items(10) {currentCount->
            ColumnItem(number = currentCount)
        }

        //items(list/array) places number of items same as the size of list/array and gives current list/array item in the lazyItemScope
        items(numbers) {arrayItem->
            ColumnItem(number = arrayItem)
        }

        //items(list/array) places number of items same as the size of list/array and gives current list/array item and currentIndex in the lazyItemScope
        itemsIndexed(numbers) { index, item ->
            ColumnItem(number = index)
        }
    }
}


//add the annotation, since [LazyVerticalGrid] is Experimental Api
@ExperimentalFoundationApi
@Composable
fun GridExample(numbers: Array<Int>) {

    //Lazy Vertical grid
    LazyVerticalGrid(
        //fix the item in one row to be 2.
        cells = GridCells.Fixed(2),

        contentPadding = PaddingValues(8.dp),

        ) {
        /**
         * All the functions to display items in
         * Lazy Vertical Grid Scope are same as discussed
         * in above LazyComposable, only difference is that
         * lazyVerticalGrid puts item in grid.
         */
        item {
            RowItem(number = 0)
        }
        items(10) {
            RowItem(number = it)
        }
        items(numbers) {
            RowItem(number = it)
        }
        itemsIndexed(numbers) { index, item ->
            RowItem(number = index)
        }
    }
}