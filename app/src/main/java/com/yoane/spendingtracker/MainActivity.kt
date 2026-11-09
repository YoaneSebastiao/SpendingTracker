// Student Name: Yoane Sebastião
// Student Number: ST10543853
// Module: IMAD5112
// Project: Spending Tracker
package com.yoane.spendingtracker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

// Four parallel arrays with seven days of sample data
val dates = arrayOf(
    "2026-10-07", "2026-10-08", "2026-10-09",
    "2026-10-10", "2026-10-11", "2026-10-12",
    "2026-10-13"
)

val dailyBudget = arrayOf(500, 250, 250, 400, 350, 300, 500)

val dailySpent = arrayOf(340, 250, 300, 200, 150, 180, 250)

val comments = arrayOf(
    "Grocery shopping",
    "Takeaways",
    "Petrol",
    "Transport",
    "Food",
    "Shopping",
    "Other expenses"
)

// Main Activity
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SpendingTrackerApp(
                onExit = { finish() }
            )
        }
    }
}

// Application navigation
@Composable
fun SpendingTrackerApp(onExit: () -> Unit) {

    var showSplash by remember { mutableStateOf(true) }
    var currentScreen by remember { mutableStateOf("home") }

    LaunchedEffect(Unit) {
        delay(3000)
        showSplash = false
    }

    if (showSplash) {
        SplashScreen()
    } else {
        when (currentScreen) {

            "home" -> MainScreen(
                onView = { currentScreen = "view" },
                onCalculate = { currentScreen = "calculate" },
                onExit = onExit
            )

            "view" -> ViewSpendingScreen(
                onBack = { currentScreen = "home" }
            )

            "calculate" -> CalculateScreen(
                onBack = { currentScreen = "home" }
            )
        }
    }
}

// Splash Screen
@Composable
fun SplashScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF102A43)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("💰", fontSize = 65.sp)

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            "SPENDING TRACKER",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFD4AF37)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            "Manage your money wisely",
            color = Color.White,
            fontSize = 15.sp
        )
    }
}

// Main Screen
@Composable
fun MainScreen(
    onView: () -> Unit,
    onCalculate: () -> Unit,
    onExit: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FA))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            "💰",
            fontSize = 50.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            "SPENDING TRACKER",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF102A43)
        )

        Text(
            "Manage your money wisely",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(35.dp))

        // Weekly budget card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF102A43)
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(22.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    "WEEKLY BUDGET",
                    color = Color.White,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "R${dailyBudget.sum()}",
                    color = Color(0xFFD4AF37),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = onView,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF102A43)
            )
        ) {
            Text("View All Spending")
        }

        Spacer(modifier = Modifier.height(14.dp))

        Button(
            onClick = onCalculate,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD4AF37)
            )
        ) {
            Text(
                "Calculate Remaining Budget",
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        OutlinedButton(
            onClick = onExit,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            Text(
                "EXIT",
                color = Color(0xFF102A43)
            )
        }
    }
}

// View Spending Screen
@Composable
fun ViewSpendingScreen(onBack: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color(0xFFF5F7FA))
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        Text(
            "Weekly Spending",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF102A43)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Loop through all seven days
        for (i in dates.indices) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        "Date: ${dates[i]}",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF102A43)
                    )

                    Text("Daily Budget: R${dailyBudget[i]}")
                    Text("Daily Spent: R${dailySpent[i]}")
                    Text("Comment: ${comments[i]}")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF102A43)
            )
        ) {
            Text("Back to Main Screen")
        }
    }
}

// Calculate Remaining Budget Screen
@Composable
fun CalculateScreen(onBack: () -> Unit) {

    // Calculate the weekly remaining budget
    var totalRemaining = 0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color(0xFFF5F7FA))
            .verticalScroll(rememberScrollState())
            .padding(18.dp)
    ) {

        Text(
            "Budget Calculation",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF102A43)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Calculate each day's remaining budget
        for (i in dates.indices) {

            val remaining = dailyBudget[i] - dailySpent[i]

            // Add the daily balance to the weekly total
            totalRemaining += remaining

            Log.d(
                "SpendingTracker",
                "${dates[i]}: Remaining budget = R$remaining"
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        dates[i],
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF102A43)
                    )

                    Text("Daily Budget: R${dailyBudget[i]}")
                    Text("Daily Spent: R${dailySpent[i]}")

                    Text(
                        "Remaining: R$remaining",
                        fontWeight = FontWeight.Bold,
                        color = if (remaining < 0) {
                            Color.Red
                        } else {
                            Color(0xFF228B22)
                        }
                    )

                    if (remaining < 0) {
                        Text(
                            "You exceeded your daily budget!",
                            color = Color.Red,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Weekly remaining budget
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF102A43)
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    "TOTAL WEEKLY REMAINING",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    "R$totalRemaining",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFD4AF37)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF102A43)
            )
        ) {
            Text("Back to Main Screen")
        }
    }
}
