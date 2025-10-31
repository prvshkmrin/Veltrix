package com.example.veltrix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.veltrix.ui.theme.VeltrixTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VeltrixTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White) // 👈 White background for entire screen
                ) { innerPadding ->
                    AppList(modifier = Modifier.padding(innerPadding))
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
            .background(Color.White) // 👈 Ensure white even inside Scaffold content
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
                appName = "Instagram"
            )
            AppIconWithName(
                iconRes = R.drawable.twitter,
                appName = "X"
            )
            AppIconWithName(
                iconRes = R.drawable.whatsapp,
                appName = "WhatsApp"
            )
        }
    }
}

@Composable
fun AppIconWithName(iconRes: Int, appName: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = appName,
            modifier = Modifier
                .size(80.dp)
                .padding(8.dp)
        )
        Text(
            text = appName,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = Color.Black // 👈 Make text readable on white
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
