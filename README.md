# Spending Tracker

## Project Overview

Spending Tracker is an Android application developed using Kotlin and Jetpack Compose. The application helps users monitor their daily expenses and calculate their remaining weekly budget.

## Features

- Splash Screen introducing the application.
- Main Screen displaying the total weekly budget.
- View All Spending screen displaying seven days of spending records.
- Budget Calculation screen showing the remaining amount for each day.
- Colour indicators for positive and negative balances.
- Automatic calculation of the total weekly remaining budget.

## Technologies Used

- Kotlin
- Android Studio
- Jetpack Compose
- Gradle

## Application Logic

The application uses four parallel arrays containing seven elements each:

- `dates` – stores the dates.
- `dailyBudget` – stores the daily budgets.
- `dailySpent` – stores the daily expenses.
- `comments` – stores descriptions of the expenses.

Loops are used to display the seven spending records and calculate the remaining budget.

The daily remaining budget is calculated by subtracting daily spending from the daily budget.

**Remaining = Daily Budget - Daily Spent**

The application also calculates the total weekly remaining balance.

## Sample Budget

- Total Weekly Budget: R2,550
- Total Weekly Remaining: R880

## Screenshots

The application includes the following screens:

1. Splash Screen
2. Main Screen
3. View All Spending
4. Budget Calculation

## How to Run the Project

1. Clone or download this repository.
2. Open the project in Android Studio.
3. Allow Gradle to sync.
4. Start an Android emulator.
5. Run the application.

## Developer

**Yoane Sebastião**

Higher Certificate in Mobile Application

## Repository

[Spending Tracker – GitHub](https://github.com/YoaneSebastiao/SpendingTracker)
