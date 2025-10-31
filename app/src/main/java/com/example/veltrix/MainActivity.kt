package com.example.veltrix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.veltrix.ui.theme.VeltrixTheme
import androidx.compose.foundation.shape.RoundedCornerShape

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VeltrixTheme {

                // 👇 State variables to control which sheet is visible
                var showInstagramSheet by remember { mutableStateOf(false) }
                var showTwitterSheet by remember { mutableStateOf(false) }
                var showWhatsAppSheet by remember { mutableStateOf(false) }

                // Entire screen uses a gradient background
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFFB2FEFA), // Light cyan
                                    Color(0xFF0ED2F7)  // Bright turquoise
                                ),
                                start = Offset(0f, 0f),
                                end = Offset(1000f, 1500f)
                            )
                        ),
                    containerColor = Color.Transparent
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        // App icons row
                        AppList(
                            onInstagramClick = { showInstagramSheet = true },
                            onTwitterClick = { showTwitterSheet = true },
                            onWhatsAppClick = { showWhatsAppSheet = true }
                        )
                    }

                    // Conditionally show each popup
                    if (showInstagramSheet) {
                        InstagramPopup(onDismiss = { showInstagramSheet = false })
                    }

                    if (showTwitterSheet) {
                        TwitterPopup(onDismiss = { showTwitterSheet = false })
                    }

                    if (showWhatsAppSheet) {
                        WhatsAppPopup(onDismiss = { showWhatsAppSheet = false })
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InstagramPopup(onDismiss: () -> Unit) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("📸 Instagram Automations", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(12.dp))
            Text("• Open Instagram")
            Text("• Post a Story")
            Text("• Send a DM")
            Text("• Check new followers")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TwitterPopup(onDismiss: () -> Unit) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("🐦 X (Twitter) Automations", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(12.dp))
            Text("• Open X")
            Text("• Post a Tweet")
            Text("• Read latest mentions")
            Text("• View trending topics")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WhatsAppPopup(onDismiss: () -> Unit) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("💬 WhatsApp Automations", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(12.dp))
            Text("• Open WhatsApp")
            Text("• Send a pre-written message")
            Text("• Schedule a message")
            Text("• Check unread chats")
        }
    }
}
