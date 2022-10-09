package com.example.jetpackanimations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackanimations.ui.theme.JetpackAnimationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackAnimationsTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    LongPressDraggable(modifier = Modifier.fillMaxSize()) {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(horizontal = 10.dp)
                        ) {
                            items(items = languages) { language ->
                                LanguageCard(language = language)
                            }
                        }
                        DescriptionCard()
                    }
                }
            }
        }
    }
}

@Composable
fun DescriptionCard() {
    val languages = remember {
        mutableStateMapOf<Int, Language>()
    }
    DropTarget<Language>(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) { isInBound, language ->
        val bgColor = if (isInBound) {
            Color.Red
        } else {
            Color.White
        }

        language?.let {
            if (isInBound)
                languages[language.id] = language
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .align(Alignment.BottomCenter)
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp))
                .background(
                    bgColor,
                    RoundedCornerShape(16.dp)
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (languages.isNotEmpty()) {
                if (language != null) {
                    Text(
                        text = language.description,
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(15.dp)
                    )
                }
            }
        }
    }
}
@Composable
fun LanguageCard(language: Language) {
    Card(
        elevation = 10.dp, backgroundColor = Color.White,
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            DragTarget(modifier = Modifier.size(130.dp), dataToDrop = language) {
                Icon(
                    painter = painterResource(id = language.image),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(130.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = language.name,
                    fontSize = 22.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}