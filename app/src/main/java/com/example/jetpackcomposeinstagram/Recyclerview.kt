package com.example.jetpackcomposeinstagram

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SuperHeroView() {
    val context = LocalContext.current
    /*
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(getResources()) { superhero ->
            ItemHero(superHero = superhero) {
                Toast.makeText(context, it.realName, Toast.LENGTH_SHORT).show()
            }
        }
    }
    */
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(getResources()) { superhero ->
            ItemHero(superHero = superhero) {
                Toast.makeText(context, it.realName, Toast.LENGTH_SHORT).show()
            }
        }
    }

}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SuperHeroStickyView() {
    val context = LocalContext.current
    val superHero : Map<String, List<SuperHero>> = getResources().groupBy { it.publisher }

    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        superHero.forEach { (publisher, mySuperHero) ->

            stickyHeader {
                Text(
                    text = publisher,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Green),
                    fontSize = 16.sp,
                    color = Color.White
                )
            }

            items(mySuperHero) { superhero ->
                ItemHero(superHero = superhero) {
                    Toast.makeText(context, it.realName, Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}

@Composable
fun SuperHeroVerticallView() {
    val context = LocalContext.current
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(getResources()) { superhero ->
            ItemHero(superHero = superhero) {
                Toast.makeText(context, it.realName, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun SuperHeroWithSpecialControlsView() {
    val context = LocalContext.current
    val rvState = rememberLazyListState()
    Column {
        LazyColumn(state = rvState, verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.weight(1f)) {
            items(getResources()) { superhero ->
                ItemHero(superHero = superhero) {
                    Toast.makeText(context, it.realName, Toast.LENGTH_SHORT).show()
                }
            }
        }

        val showButton by remember {
            derivedStateOf {
                rvState.firstVisibleItemIndex > 0
            }
        }
        
        Button(onClick = {
            Toast.makeText(context, "click on button", Toast.LENGTH_SHORT).show()
        }, modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(16.dp)) {
            Text(text = "Un botton cool")
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SuperHeroGridView() {
    val context = LocalContext.current
    LazyVerticalGrid(cells = GridCells.Fixed(3), content = {
        items(getResources()) { superhero ->
            ItemHero(superHero = superhero) {
                Toast.makeText(context, it.realName, Toast.LENGTH_SHORT).show()
            }
        }
        //otra forma de hacer padding a items
    }, contentPadding = PaddingValues(horizontal = 2.dp , vertical = 4.dp))

    /*
    *     val context = LocalContext.current
    LazyVerticalGrid(cells = GridCells.Adaptive(100.dp), content = {
        items(getResources()) { superhero ->
            ItemHero(superHero = superhero) {
                Toast.makeText(context, it.realName, Toast.LENGTH_SHORT).show()
            }
        }

    })

    *
    * */

}

@Composable
fun ItemHero(superHero: SuperHero, onItemSelected: (SuperHero) -> Unit) {
    Card(
        border = BorderStroke(2.dp, Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemSelected(superHero) }
            .padding(top = 8.dp, bottom = 8.dp, end = 8.dp, start = 8.dp)) {
        Column {
            Image(
                painter = painterResource(id = superHero.photo),
                contentDescription = "SuperHero Avatar",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = superHero.superHeroName,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = superHero.realName,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 12.sp
            )
            Text(
                text = superHero.publisher,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(8.dp),
                fontSize = 10.sp
            )
        }

    }
}

fun getResources(): List<SuperHero> {
    return listOf(
        SuperHero("Spider man", "petter Parker", "Marvel", R.drawable.spiderman),
        SuperHero("Wolverine ", "James Howleft ", "Marvel", R.drawable.logan),
        SuperHero("Batman ", "Bruce wayne ", "DC", R.drawable.batman),
        SuperHero("Batman ", "Bruce wayne ", "DC", R.drawable.batman),
        SuperHero("Batman ", "Bruce wayne ", "DC", R.drawable.batman),
        SuperHero("Batman ", "Bruce wayne ", "DC", R.drawable.batman),
        SuperHero("Batman ", "Bruce wayne ", "DC", R.drawable.batman),
        SuperHero("Batman ", "Bruce wayne ", "DC", R.drawable.batman),
        SuperHero("Thor ", "Thor Odison ", "Marvel", R.drawable.thor)
    )
}

data class SuperHero(
    var superHeroName: String,
    var realName: String,
    var publisher: String,
    @DrawableRes var photo: Int
)
