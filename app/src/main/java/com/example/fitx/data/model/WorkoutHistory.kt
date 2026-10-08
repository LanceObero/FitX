package com.example.fitx.data.model

data class WorkoutHistory(
    val category: String,
    val bodyPart: String,
    val calories: Int,
    val minutes: Int,
    val date: String
)