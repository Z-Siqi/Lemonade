package com.sqz.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sqz.lemonade.ui.theme.LemonadeTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeView()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeView(modifier: Modifier = Modifier) {
    var randomValue by remember { mutableStateOf(0) }
    var step by remember { mutableStateOf(1) }
    val image = when (step) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_squeeze
        4 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val stepText = when (step) {
        1 -> R.string.Lemon_tree
        2 -> R.string.Lemon
        3 -> R.string.Lemon
        4 -> R.string.Glass_of_lemonade
        else -> R.string.Empty_glass
    }
    if (step == 3) {
        step = 2
        randomValue++
    }
    if (step == 6) {
        step = 1
    }
    if (randomValue == 1) {
        randomValue += (1..2).random()
    }
    if (randomValue > 4) {
        step = 4
        randomValue = 0
    }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(stepText),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight(1000)
        )
        Spacer(modifier = modifier.height(16.dp))
        Image(
            painter = painterResource(id = image),
            contentDescription = step.toString(),
            modifier = modifier
                .clickable { step++ }
                .border(2.dp, Color(0xFF69CDD8), RoundedCornerShape(10.dp))
        )
        Spacer(modifier = modifier.height(16.dp))
    }
}