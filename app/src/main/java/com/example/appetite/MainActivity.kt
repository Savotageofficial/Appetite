package com.example.appetite

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appetite.ui.theme.AppetiteTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle

val recipies = arrayListOf(
    Recipe(
        image = R.drawable.pasta_recipe,
        difficulty = Easy.Dif,
        difficultycolor = Easy.color,
        title = "Creamy Tomato Pasta",
        Description = "A quick and delicious meal , ready in under 30 minutes",
    ),
    Recipe(
        image = R.drawable.chocolate_cake,
        difficulty = Medium.Dif,
        difficultycolor = Medium.color,
        title = "Ultimate Chocolate Cake",
        Description = "Indulge in this rich and decadent dessert for special occasions",
    ),
    Recipe(
        image = R.drawable.lasagne_recipe,
        difficulty = Hard.Dif,
        difficultycolor = Hard.color,
        title = "Classic Beef Lasagna",
        Description = "A comforting classic that's worth the effort",
    ),

    )
data class Recipe(
    val image: Int,
    val difficultycolor: Color,
    val difficulty: String,
    val title: String,
    val Description: String
)

class Easy {
    companion object {
        val color: Color = Color(0xFF4CAF50)
        val Dif: String = "Easy"
    }
}
class Medium {
    companion object {
        val color: Color = Color(0xFFeab308)
        val Dif: String = "Medium"
    }
}
class Hard{
    companion object {
        val color: Color = Color(0xFFef4444)
        val Dif: String = "Hard"
    }
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppetiteTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ScrollableHomeScreen(modifier = Modifier.padding(innerPadding) , onclick = {
                        Toast.makeText(this, "Upcoming", Toast.LENGTH_SHORT).show()
                    })
                }
            }
        }
    }
}

@Composable
fun ScrollableHomeScreen( modifier: Modifier = Modifier , onclick :() -> Unit) {
    val gradientBackground = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFffc993),
            Color(0xFFfff7ef)
        )
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(brush = gradientBackground)
            .padding(horizontal = 20.dp), // Apply horizontal padding here
        contentPadding = PaddingValues(top = 20.dp) // Apply top padding for the header
    ) {
        // --- Header Items ---
        item {
            Text(
                text = "Appetite",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
        }
//        item { Spacer(modifier = Modifier.height(5.dp)) }

        item {
            Text(
                modifier = modifier.fillMaxWidth(),
                text = "What to cook today?",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
        item { Spacer(modifier = Modifier.height(30.dp)) }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp), // No need for start/end padding if applied to LazyColumn
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MyButton(imageVector = Icons.Default.FavoriteBorder , title = "Favourites" , onclick = onclick)
                MyButton(imageVector = ImageVector.vectorResource(R.drawable.baseline_fastfood_24) , title = "Categories" , onclick = onclick)
                MyButton(imageVector = Icons.Default.AccountCircle , title = "Profile" , onclick = onclick)
            }
        }
        item { Spacer(modifier = Modifier.height(35.dp)) }

        item {
            Text(
                text = "Today's Inspiration",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        item { Spacer(modifier = Modifier.height(10.dp)) } // Add a small spacer before the list starts

        // --- Recipe List Items ---
        items(items = recipies) { item ->
            ItemCard(
                Title = item.title,
                Description = item.Description,
                image = item.image,
                difficulty = item.difficulty, // Use item's difficulty, not always Easy.Dif
                difficultycolor = item.difficultycolor // Use item's color, not always Easy.color
            )
        }
    }
}
@Composable
fun MyButton(modifier: Modifier = Modifier , imageVector : ImageVector , title : String , onclick : () -> Unit){
    Button(
        onClick = onclick,
        modifier = modifier
            .width(100.dp)
            .height(85.dp),
            shape = RoundedCornerShape(10.dp),
            contentPadding = PaddingValues(start = 0.dp , end = 0.dp),
            colors = ButtonColors(
                containerColor = Color(0xFFFFFFFF),
                contentColor = Color(0xFF000000),
                disabledContainerColor = ButtonDefaults.buttonColors().disabledContainerColor,
                disabledContentColor = ButtonDefaults.buttonColors().disabledContentColor
            ),
        border = BorderStroke(width = 2.dp , color = Color(0xFFb0aeab))


    ){
        Column(modifier = modifier
            .fillMaxSize()
            .padding(top = 10.dp, bottom = 10.dp)
        ) {
            Spacer(modifier = modifier.height(7.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = modifier.fillMaxWidth()
            ){
                Icon(imageVector = imageVector , contentDescription = "" , tint = Color(0xFFf38e2a))
            }
            Spacer(modifier = modifier.height(5.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = modifier.fillMaxWidth()
            ){
                Text(
                    text = title,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 10.sp

                )
            }


        }

    }


}


@Composable
fun DifficultyTag(difficulty: String , color: Color) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp)) // Rounded corners for the tag
            .background(color)
            .padding(horizontal = 10.dp, vertical = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = difficulty,
            color = Color.White,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp
            )
        )
    }
}
@Composable
fun ItemCard(modifier: Modifier = Modifier, Title : String , Description : String , backgroundColor : Color = Color(0xFFFFFAF2) , image: Int , difficulty: String , difficultycolor: Color){
    Card(modifier
        .padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 30.dp)
        .fillMaxWidth()
        .height(250.dp)
        .clip(RoundedCornerShape(20.dp))    , colors = CardDefaults.cardColors(
        containerColor = backgroundColor
    ),
        elevation = CardDefaults.cardElevation(50.dp),



    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .weight(3f)
                // Clip only the top corners
                .clip(
                    RoundedCornerShape(20.dp).copy(
                        bottomStart = CornerSize(0.dp),
                        bottomEnd = CornerSize(0.dp)
                    )
                )
        ) {
            // In a real app, replace `painterResource` with an async image loader (like Coil)
            // that fetches an image URL. Using a placeholder image for this example.
            Image(
                painter = painterResource(id = image),
                contentDescription = "pasta",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
        }
//        Image(
//            painter = painterResource(R.drawable.pasta_recipe),
//            contentDescription = "pasta",
//            modifier = modifier.weight(3f)
//                .fillMaxWidth()
//        )
        Column(
            modifier
                .padding(10.dp)
                .fillMaxSize()
                .weight(2f)
        ){


            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    style = TextStyle(
                        letterSpacing = (-0.3).sp
                    ),
                    text = Title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.height(30.dp),
                    color = Color(0xff000000),
                    lineHeight = 20.sp
                )

                DifficultyTag(difficulty = difficulty , color = difficultycolor)

            }

            Text(text = Description , fontSize = 15.sp , color = Color(0xFF8b6743))



        }

    }


}
@Preview(showBackground = true , showSystemUi = true)
@Composable
fun ScrollableHomeScreenPreview() {
    AppetiteTheme {
        ScrollableHomeScreen(onclick = {
        })
    }
}
//@Preview(showBackground = true)
//@Composable
//fun MyButtonPreview() {
//    AppetiteTheme {
//        MyButton(imageVector = Icons.Default.FavoriteBorder , title = "Favourites")
//        ItemCard(Title = "Creamy Tomato Pasta" , Description = "A quick and delicious meal , ready in under 30 minutes" , image = R.drawable.pasta_recipe , difficultycolor = Easy.color , difficulty = Easy.Dif)
//    }
//}