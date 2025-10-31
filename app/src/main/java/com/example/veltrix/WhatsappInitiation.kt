package com.example.veltrix

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import com.example.veltrix.ui.theme.VeltrixTheme
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

class WhatsappInitiation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VeltrixTheme {
                WhatsappInitiationScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WhatsappInitiationScreen() {
    // Tabs
    val tabs = listOf("Pending", "Done", "Failed")
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }

    // Sheet visibility state
    var showSheet by remember { mutableStateOf(false) }

    // Modal bottom sheet state
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showSheet = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add task"
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        // Show ModalBottomSheet when requested
        if (showSheet) {
            ModalBottomSheet(
                onDismissRequest = { showSheet = false },
                sheetState = sheetState,
                containerColor = Color.White,
                tonalElevation = 8.dp,
                dragHandle = null
            ) {
                BottomSheetContent(
                    onItemClick = { name, context ->
                        showSheet = false
                        Toast.makeText(context, "$name clicked", Toast.LENGTH_SHORT).show()
                    },
                    onCancel = { showSheet = false }
                )
            }
        }

        // Background gradient + content container
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFB2FEFA),
                            Color(0xFF0ED2F7)
                        ),
                        start = Offset(0f, 0f),
                        end = Offset(1000f, 1500f)
                    )
                )
                .padding(innerPadding)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.height(16.dp))

                // Tab Row (Pending / Done / Failed)
                TabRow(
                    selectedTabIndex = selectedTab,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    containerColor = Color.Transparent,
                    indicator = { tabPositions ->
                        TabRowDefaults.SecondaryIndicator(
                            Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                            height = 3.dp
                        )
                    }
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = { Text(title) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Content area for each tab
                when (selectedTab) {
                    0 -> TaskList(tasks = samplePendingTasks())
                    1 -> TaskList(tasks = sampleDoneTasks())
                    2 -> TaskList(tasks = sampleFailedTasks())
                }
            }
        }
    }
}

@Composable
fun BottomSheetContent(
    onItemClick: (String, android.content.Context) -> Unit,
    onCancel: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Small drag indicator
        Box(
            modifier = Modifier
                .width(36.dp)
                .height(4.dp)
                .background(
                    color = Color(0xFFE0E0E0),
                    shape = RoundedCornerShape(2.dp)
                )
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Choose an action",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Item: Swamp
            Column(
                modifier = Modifier
                    .width(80.dp)
                    .clickable { onItemClick("Swamp", context) }
                    .padding(vertical = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.swamp),
                    contentDescription = "Swamp",
                    modifier = Modifier.size(56.dp)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Swamp",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            }

            // Item: Reply
            Column(
                modifier = Modifier
                    .width(80.dp)
                    .clickable { onItemClick("Reply", context) }
                    .padding(vertical = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.reply),
                    contentDescription = "Reply",
                    modifier = Modifier.size(56.dp)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Reply",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            }

            // Item: Schedule
            Column(
                modifier = Modifier
                    .width(80.dp)
                    .clickable { onItemClick("Schedule", context) }
                    .padding(vertical = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.schedule),
                    contentDescription = "Schedule",
                    modifier = Modifier.size(56.dp)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Schedule",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Cancel button
        OutlinedButton(
            onClick = { onCancel() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancel")
        }
    }
}

@Composable
fun TaskList(tasks: List<String>) {
    if (tasks.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(text = "No tasks here", style = MaterialTheme.typography.bodyLarge)
        }
        return
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(tasks) { task ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                shape = MaterialTheme.shapes.medium,
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = task, style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Tap for details", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}

// --- Sample placeholder data (replace with real data) ---
@Composable
fun samplePendingTasks(): List<String> {
    return remember {
        listOf(
            "Reply to message from Ravi",
            "Schedule meeting with design team",
            "Backup chat history"
        )
    }
}

@Composable
fun sampleDoneTasks(): List<String> {
    return remember {
        listOf(
            "Sent project update",
            "Fixed notification bug"
        )
    }
}

@Composable
fun sampleFailedTasks(): List<String> {
    return remember {
        listOf(
            "Failed to upload attachment",
            "Message send error"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WhatsappInitiationPreview() {
    VeltrixTheme {
        WhatsappInitiationScreen()
    }
}