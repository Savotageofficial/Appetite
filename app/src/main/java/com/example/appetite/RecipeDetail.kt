package com.example.appetite

import android.os.Bundle
import android.widget.Space
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toDrawable
import com.example.appetite.ui.theme.AppetiteTheme
val Ingredients = listOf<String>(
    "ingredient 1",
    "ingredient 2",
    "ingredient 3",
    "ingredient 4",
    "ingredient 5"
)

val Preparation = listOf<String>(
    "Step 1",
    "Step 2",
    "Step 3",
    "Step 4",
    "Step 5"
)
class RecipeDetail : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {
            AppetiteTheme {
                Scaffold(modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
                ) { innerPadding ->
                    RecipeDetailScreen(
                        modifier = Modifier.padding(innerPadding),
                        onbackclick = {},
                        image = R.drawable.lasagne_recipe,
                        title = "Classic Beef Lasagna",
                        onfavclick = {
                            Toast.makeText(this , "Added to favourites" , Toast.LENGTH_SHORT).show()
                        },
                        ingredients = Ingredients,
                        Preparation = Preparation
                    )
                }
            }
        }
    }
}

@Composable
fun RecipeDetailScreen(modifier: Modifier = Modifier , onbackclick: () -> Unit , onfavclick : () -> Unit ,image: Int , title : String ,  ingredients: List<String> , Preparation: List<String>) {
    val gradientBackground = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFffc993),
            Color(0xFFfff7ef)
        )
    )
    val gradientBottomBackground = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFFFFFFF),
            Color(0xFFFFE6D1)
        )
    )


    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(brush = gradientBackground)
            .padding(horizontal = 20.dp), // Apply horizontal padding here
        contentPadding = PaddingValues(top = 20.dp) // Apply top padding for the header
    ){
        item{
            Row(
                modifier = modifier.fillMaxWidth()
            ) {
                IconButton(
                    content = {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack , contentDescription = "back button" , modifier = modifier.fillMaxSize())
                    } ,
                    onClick = onbackclick,
                    modifier = modifier.height(30.dp)
                )

                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(30.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Recipe" ,
                        modifier = modifier.padding(end = 50.dp),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 22.sp
                    )
                }

            }
        }
        item {
            Spacer(modifier = modifier.height(35.dp))
        }
        item{
            Image(
                painter = painterResource(image) ,
                contentDescription = "",
                modifier = modifier.clip(RoundedCornerShape(15.dp))
            )
        }

        item {
            Spacer(modifier = modifier.height(25.dp))
        }
        item {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .background(brush = gradientBottomBackground)
            ) {
                Column(
                    modifier = modifier.padding(15.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        )
                        FavBtn(
                            onclick = onfavclick,
                            text = "Add to Favourites"
                        )
                    }
                    Spacer(modifier = modifier.height(1.dp))


                    Spacer(modifier = modifier.height(24.dp))

                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {

                        DisplayCard(title = "Difficulty" , content = "Easy")
                        DisplayCard(title = "Serving Size" , content = "Serves 4")




                    }

                    Spacer(modifier = modifier.height(20.dp))
                    CategoryNavigator(ingredients = Ingredients , preparation = Preparation)

                }



            }
        }


    }
}


@Composable
fun FavBtn(modifier: Modifier = Modifier , text : String ,onclick: () -> Unit){
    Column(


        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Button(

            modifier = modifier.size(55.dp),
            contentPadding = PaddingValues(10.dp),

            colors = ButtonColors(Color(0xFFFFD9BC), contentColor = ButtonDefaults.buttonColors().contentColor , disabledContentColor = ButtonDefaults.buttonColors().disabledContentColor , disabledContainerColor = ButtonDefaults.buttonColors().disabledContainerColor),
            onClick = onclick,
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(tint = Color(0xFFf38e2a),
                imageVector = Icons.Default.Favorite ,
                contentDescription = "favourite icon",
            )

        }

//        Text(text = text , letterSpacing = (-0.1).sp)


    }

}
@Composable
fun DisplayCard(modifier: Modifier = Modifier , title : String , content : String){
    Card(
        modifier = modifier
            .width(140.dp)
            .height(85.dp),
        shape = RoundedCornerShape(10.dp),
//        contentPadding = PaddingValues(start = 0.dp , end = 0.dp),
        colors = CardColors(
            containerColor = Color(0xFFFFFFFF),
            contentColor = Color(0xFF000000),
            disabledContainerColor = ButtonDefaults.buttonColors().disabledContainerColor,
            disabledContentColor = ButtonDefaults.buttonColors().disabledContentColor
        ),
        border = BorderStroke(width = 1.dp , color = Color(0xFFb0aeab))


    ){
        Column(modifier = modifier
            .fillMaxSize()
            .padding(top = 10.dp, bottom = 10.dp)
        ) {
            Spacer(modifier = modifier.height(7.dp))
            Row(

                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            ){
                Text(
                    text = title ,
                    fontSize = 15.sp

                )

            }
            Spacer(modifier = modifier.height(5.dp))
            Row(

                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            ){
                Text(
                    text = content,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 15.sp

                )
            }


        }

    }


}


@Preview(showBackground = true , showSystemUi = true)
@Composable
fun RecipeDetailScreenPreview() {
    AppetiteTheme {
        RecipeDetailScreen(onbackclick = {} , image = R.drawable.lasagne_recipe , title = "Classic Beef Lasagna" , onfavclick = {} , ingredients = Ingredients , Preparation = Preparation)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryNavigator(modifier: Modifier = Modifier , ingredients: List<String> , preparation : List<String>) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Ingredients", "Preparation")

    Column(modifier = Modifier.fillMaxSize()) {
        // Tab Row
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.fillMaxWidth(),
            contentColor = Color(0xFFf38e2a),
            indicator = { tabPositions: List<TabPosition> ->

                    TabRowDefaults.SecondaryIndicator(
                        // Use the default offset logic
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex])
                            .height(4.dp), // **Sets the height/thickness**
                        color = Color(0xFFf38e2a) // **Sets the color**
                    )
            },

            containerColor = Color(alpha = 0 , red = 0 , blue = 0 , green = 0)
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title , fontSize = 15.sp) },
                    selectedContentColor = Color(0xFFf38e2a),
                    unselectedContentColor = Color(0xff909090),
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index }
                )
            }
        }

        // Content based on selected tab
        when (selectedTabIndex) {
            0 -> IngredientsContent(ingredients = ingredients)
            1 -> PreparationContent(preparation = preparation)
        }
    }
}

@Composable
fun IngredientsContent(modifier: Modifier = Modifier , ingredients: List<String>){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        LazyColumn{
            items(ingredients){item ->

                Spacer(modifier = modifier.height(15.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_fiber_manual_record_24) ,
                        contentDescription = "dot" ,
                        tint = Color(0xFFf38e2a),
                        modifier = modifier.size(12.dp)
                    )
                    Spacer(modifier = modifier.width(20.dp))
                    Text(text = item , fontSize = 17.sp)
                }


            }
        }

    }
}


@Composable
fun PreparationContent(modifier: Modifier = Modifier , preparation: List<String>){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        LazyColumn{
            items(preparation){item ->

                Spacer(modifier = modifier.height(15.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_fiber_manual_record_24) ,
                        contentDescription = "dot" ,
                        tint = Color(0xFFf38e2a),
                        modifier = modifier.size(12.dp)
                    )
                    Spacer(modifier = modifier.width(20.dp))
                    Text(text = item , fontSize = 17.sp)
                }


            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun FavBtnPreview() {
    AppetiteTheme {
        FavBtn(onclick = {} , text = "Add to favourites")
    }
}
@Preview(showBackground = true)
@Composable
fun DisplayCardPreview() {
    AppetiteTheme {
        DisplayCard(title = "Difficulty" , content = "Easy")
    }
}