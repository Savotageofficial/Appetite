package com.example.appetite

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appetite.ui.theme.AppetiteTheme

class RecipeDetail : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppetiteTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RecipeDetailScreen(
                        modifier = Modifier.padding(innerPadding),
                        onbackclick = {},
                        image = R.drawable.lasagne_recipe,
                        title = "Classic Beef Lasagna"
                    )
                }
            }
        }
    }
}

@Composable
fun RecipeDetailScreen(modifier: Modifier = Modifier , onbackclick: () -> Unit , image: Int , title : String) {
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
                    modifier = modifier.fillMaxWidth()
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
                modifier = modifier.fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 20.dp , topEnd = 20.dp))
                    .background(brush = gradientBottomBackground)
            ) {
                Column(
                    modifier = modifier.padding(15.dp)
                ) {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )

                }



            }
        }


    }
}

@Preview(showBackground = true , showSystemUi = true)
@Composable
fun RecipeDetailScreenPreview() {
    AppetiteTheme {
        RecipeDetailScreen(onbackclick = {} , image = R.drawable.lasagne_recipe , title = "Classic Beef Lasagna")
    }
}