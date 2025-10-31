package com.example.veltrix

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.veltrix.ui.theme.VeltrixTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VeltrixTheme {
                // use the Activity reference to start new activities from composables
                val activity = this@MainActivity

                // 🌈 Full screen gradient background
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFFB2FEFA), // light cyan
                                    Color(0xFF0ED2F7)  // turquoise
                                ),
                                start = Offset(0f, 0f),
                                end = Offset(1000f, 1500f)
                            )
                        ),
                    containerColor = Color.Transparent
                ) { innerPadding ->

                    // Center icons on screen
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        AppList(
                            onInstagramClick = {
                                val i = Intent(activity, instagramInitiation::class.java)
                                activity.startActivity(i)
                            },
                            onTwitterClick = {
                                val i = Intent(activity, xInitiation::class.java)
                                activity.startActivity(i)
                            },
                            onWhatsAppClick = {
                                val i = Intent(activity, whatsappInitiation::class.java)
                                activity.startActivity(i)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AppList(
    modifier: Modifier = Modifier,
    onInstagramClick: () -> Unit,
    onTwitterClick: () -> Unit,
    onWhatsAppClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppIconWithName(
            iconRes = R.drawable.instagram,
            appName = "Instagram",
            onClick = { onInstagramClick() }
        )

        AppIconWithName(
            iconRes = R.drawable.twitter,
            appName = "X",
            onClick = { onTwitterClick() }
        )

        AppIconWithName(
            iconRes = R.drawable.whatsapp,
            appName = "WhatsApp",
            onClick = { onWhatsAppClick() }
        )
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
