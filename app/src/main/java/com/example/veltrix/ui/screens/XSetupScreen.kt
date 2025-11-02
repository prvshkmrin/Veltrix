package com.example.veltrix.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.veltrix.ui.theme.VeltrixTheme

class XInitiation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VeltrixTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ComingSoonScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ComingSoonScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Coming Soon",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray.copy(alpha = 0.7f) // Slightly faded gray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ComingSoonPreview() {
    VeltrixTheme {
        ComingSoonScreen()
    }
}
