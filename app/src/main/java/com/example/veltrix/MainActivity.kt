package com.example.veltrix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.veltrix.ui.theme.VeltrixTheme
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.geometry.Offset

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VeltrixTheme {
                // Root box to hold gradient + scaffold
                Box(modifier = Modifier.fillMaxSize()) {

                    // Gradient background layer
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(Color.Cyan, Color.White),
                                    start = Offset(0f, 0f),
                                    end = Offset(1000f, 1000f)
                                )
                            )
                    )

                    // Foreground content layer (Scaffold)
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        containerColor = Color.Transparent // important so background shows through
                    ) { innerPadding ->
                        AppList(modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}

@Composable
fun AppList(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppIconWithName(
                iconRes = R.drawable.instagram,
                appName = "Instagram",
                onClick = null
            )

            AppIconWithName(
                iconRes = R.drawable.twitter,
                appName = "X",
                onClick = null
            )

            AppIconWithName(
                iconRes = R.drawable.whatsapp,
                appName = "WhatsApp",
                onClick = null
            )
        }
    }
}

@Composable
fun AppIconWithName(
    iconRes: Int,
    appName: String,
    onClick: (() -> Unit)? = null
) {
    val imageModifier = if (onClick != null) {
        Modifier
            .size(80.dp)
            .padding(8.dp)
            .clickable { onClick() }
    } else {
        Modifier
            .size(80.dp)
            .padding(8.dp)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = appName,
            modifier = imageModifier
        )
        Text(
            text = appName,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppListPreview() {
    VeltrixTheme {
        AppList()
    }
}
