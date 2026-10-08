package com.example.fitx.data.repository

import com.example.fitx.data.model.Exercise

object WorkoutData {

    fun getExercises(
        bodyPart: String,
        goal: String
    ): List<Exercise> {

        return when (bodyPart) {

            "Chest" -> when (goal) {

                "Build Strength" -> listOf(
                    Exercise("Push-ups", "8 reps", "12 reps", "20 reps"),
                    Exercise("Diamond Push-ups", "5 reps", "8 reps", "15 reps"),
                    Exercise("Wide Push-ups", "8 reps", "12 reps", "20 reps"),
                    Exercise("Incline Push-ups", "10 reps", "15 reps", "20 reps")
                )

                "Lose Weight" -> listOf(
                    Exercise("Push-ups", "8 reps", "12 reps", "20 reps"),
                    Exercise("Mountain Climbers", "10 reps", "20 reps", "30 reps"),
                    Exercise("Wide Push-ups", "8 reps", "12 reps", "20 reps"),
                    Exercise("Jumping Jacks", "20 sec", "30 sec", "45 sec")
                )

                "Improve Endurance" -> listOf(
                    Exercise("Push-ups", "8 reps", "12 reps", "20 reps"),
                    Exercise("Wide Push-ups", "8 reps", "12 reps", "20 reps"),
                    Exercise("Incline Push-ups", "10 reps", "15 reps", "20 reps"),
                    Exercise("Plank", "20 sec", "30 sec", "60 sec")
                )

                else -> listOf(
                    Exercise("Push-ups", "8 reps", "12 reps", "20 reps"),
                    Exercise("Wide Push-ups", "8 reps", "12 reps", "20 reps"),
                    Exercise("Incline Push-ups", "10 reps", "15 reps", "20 reps"),
                    Exercise("Diamond Push-ups", "5 reps", "8 reps", "15 reps")
                )
            }

            "Arms" -> when (goal) {

                "Build Strength" -> listOf(
                    Exercise("Tricep Dips", "8 reps", "12 reps", "20 reps"),
                    Exercise("Diamond Push-ups", "5 reps", "8 reps", "15 reps"),
                    Exercise("Plank Shoulder Taps", "8 reps", "12 reps", "20 reps"),
                    Exercise("Arm Circles", "20 sec", "30 sec", "45 sec")
                )

                "Lose Weight" -> listOf(
                    Exercise("Arm Circles", "20 sec", "30 sec", "45 sec"),
                    Exercise("Plank Shoulder Taps", "8 reps", "12 reps", "20 reps"),
                    Exercise("Mountain Climbers", "10 reps", "20 reps", "30 reps"),
                    Exercise("Jumping Jacks", "20 sec", "30 sec", "45 sec")
                )

                "Improve Endurance" -> listOf(
                    Exercise("Arm Circles", "20 sec", "30 sec", "45 sec"),
                    Exercise("Tricep Dips", "8 reps", "12 reps", "20 reps"),
                    Exercise("Plank Shoulder Taps", "8 reps", "12 reps", "20 reps"),
                    Exercise("Diamond Push-ups", "5 reps", "8 reps", "15 reps")
                )

                else -> listOf(
                    Exercise("Tricep Dips", "8 reps", "12 reps", "20 reps"),
                    Exercise("Diamond Push-ups", "5 reps", "8 reps", "15 reps"),
                    Exercise("Arm Circles", "20 sec", "30 sec", "45 sec"),
                    Exercise("Plank Shoulder Taps", "8 reps", "12 reps", "20 reps")
                )
            }

            "Legs" -> when (goal) {

                "Build Strength" -> listOf(
                    Exercise("Squats", "10 reps", "15 reps", "25 reps"),
                    Exercise("Lunges", "8 reps", "12 reps", "20 reps"),
                    Exercise("Glute Bridges", "10 reps", "15 reps", "25 reps"),
                    Exercise("Calf Raises", "15 reps", "20 reps", "30 reps")
                )

                "Lose Weight" -> listOf(
                    Exercise("Squats", "10 reps", "15 reps", "25 reps"),
                    Exercise("Jumping Jacks", "20 sec", "30 sec", "45 sec"),
                    Exercise("Lunges", "8 reps", "12 reps", "20 reps"),
                    Exercise("Mountain Climbers", "10 reps", "20 reps", "30 reps")
                )

                "Improve Endurance" -> listOf(
                    Exercise("Squats", "10 reps", "15 reps", "25 reps"),
                    Exercise("Lunges", "8 reps", "12 reps", "20 reps"),
                    Exercise("Calf Raises", "15 reps", "20 reps", "30 reps"),
                    Exercise("Jumping Jacks", "20 sec", "30 sec", "45 sec")
                )

                else -> listOf(
                    Exercise("Squats", "10 reps", "15 reps", "25 reps"),
                    Exercise("Lunges", "8 reps", "12 reps", "20 reps"),
                    Exercise("Glute Bridges", "10 reps", "15 reps", "25 reps"),
                    Exercise("Calf Raises", "15 reps", "20 reps", "30 reps")
                )
            }

            "Core" -> when (goal) {

                "Build Strength" -> listOf(
                    Exercise("Plank", "20 sec", "30 sec", "60 sec"),
                    Exercise("Crunches", "10 reps", "15 reps", "25 reps"),
                    Exercise("Leg Raises", "6 reps", "10 reps", "15 reps"),
                    Exercise("Mountain Climbers", "10 reps", "20 reps", "30 reps")
                )

                "Lose Weight" -> listOf(
                    Exercise("Mountain Climbers", "10 reps", "20 reps", "30 reps"),
                    Exercise("Crunches", "10 reps", "15 reps", "25 reps"),
                    Exercise("Plank", "20 sec", "30 sec", "60 sec"),
                    Exercise("Jumping Jacks", "20 sec", "30 sec", "45 sec")
                )

                "Improve Endurance" -> listOf(
                    Exercise("Plank", "20 sec", "30 sec", "60 sec"),
                    Exercise("Mountain Climbers", "10 reps", "20 reps", "30 reps"),
                    Exercise("Leg Raises", "6 reps", "10 reps", "15 reps"),
                    Exercise("Crunches", "10 reps", "15 reps", "25 reps")
                )

                else -> listOf(
                    Exercise("Plank", "20 sec", "30 sec", "60 sec"),
                    Exercise("Crunches", "10 reps", "15 reps", "25 reps"),
                    Exercise("Leg Raises", "6 reps", "10 reps", "15 reps"),
                    Exercise("Mountain Climbers", "10 reps", "20 reps", "30 reps")
                )
            }

            "Shoulders" -> listOf(
                Exercise("Pike Push-ups", "5 reps", "10 reps", "15 reps"),
                Exercise("Shoulder Taps", "8 reps", "12 reps", "20 reps"),
                Exercise("Arm Circles", "20 sec", "30 sec", "45 sec"),
                Exercise("Wall Push-ups", "10 reps", "15 reps", "25 reps")
            )

            "Back" -> listOf(
                Exercise("Superman", "8 reps", "12 reps", "20 reps"),
                Exercise("Bird Dog", "8 reps", "10 reps", "15 reps"),
                Exercise("Reverse Snow Angel", "8 reps", "12 reps", "20 reps"),
                Exercise("Swimmer", "15 sec", "20 sec", "30 sec")
            )

            "Full Body" -> when (goal) {

                "Build Strength" -> listOf(
                    Exercise("Squats", "10 reps", "15 reps", "25 reps"),
                    Exercise("Push-ups", "8 reps", "12 reps", "20 reps"),
                    Exercise("Lunges", "8 reps", "12 reps", "20 reps"),
                    Exercise("Plank", "20 sec", "30 sec", "60 sec"),
                    Exercise("Mountain Climbers", "10 reps", "20 reps", "30 reps")
                )

                "Lose Weight" -> listOf(
                    Exercise("Jumping Jacks", "20 sec", "30 sec", "45 sec"),
                    Exercise("Mountain Climbers", "10 reps", "20 reps", "30 reps"),
                    Exercise("Squats", "10 reps", "15 reps", "25 reps"),
                    Exercise("Push-ups", "8 reps", "12 reps", "20 reps"),
                    Exercise("Plank", "20 sec", "30 sec", "60 sec")
                )

                "Improve Endurance" -> listOf(
                    Exercise("Jumping Jacks", "20 sec", "30 sec", "45 sec"),
                    Exercise("Squats", "10 reps", "15 reps", "25 reps"),
                    Exercise("Push-ups", "8 reps", "12 reps", "20 reps"),
                    Exercise("Mountain Climbers", "10 reps", "20 reps", "30 reps"),
                    Exercise("Plank", "20 sec", "30 sec", "60 sec")
                )

                else -> listOf(
                    Exercise("Jumping Jacks", "20 sec", "30 sec", "45 sec"),
                    Exercise("Squats", "10 reps", "15 reps", "25 reps"),
                    Exercise("Push-ups", "8 reps", "12 reps", "20 reps"),
                    Exercise("Mountain Climbers", "10 reps", "20 reps", "30 reps"),
                    Exercise("Plank", "20 sec", "30 sec", "60 sec")
                )
            }

            else -> emptyList()
        }
    }
}