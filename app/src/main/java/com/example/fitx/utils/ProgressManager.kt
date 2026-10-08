package com.example.fitx.utils

import android.content.Context
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object ProgressManager {

    private const val PREF_NAME =
        "fitx_progress"

    private const val WORKOUT_COUNT =
        "workout_count"

    private const val CALORIES =
        "calories"

    private const val MINUTES =
        "minutes"

    fun getWorkoutCount(
        context: Context
    ): Int {

        return getPreferences(context)
            .getInt(
                WORKOUT_COUNT,
                0
            )
    }

    fun getCalories(
        context: Context
    ): Int {

        return getPreferences(context)
            .getInt(
                CALORIES,
                0
            )
    }

    fun getMinutes(
        context: Context
    ): Int {

        return getPreferences(context)
            .getInt(
                MINUTES,
                0
            )
    }

    fun completeWorkout(
        context: Context,
        calories: Int,
        minutes: Int
    ) {

        val preferences =
            getPreferences(context)

        val currentWorkouts =
            preferences.getInt(
                WORKOUT_COUNT,
                0
            )

        val currentCalories =
            preferences.getInt(
                CALORIES,
                0
            )

        val currentMinutes =
            preferences.getInt(
                MINUTES,
                0
            )

        preferences.edit()
            .putInt(
                WORKOUT_COUNT,
                currentWorkouts + 1
            )
            .putInt(
                CALORIES,
                currentCalories + calories
            )
            .putInt(
                MINUTES,
                currentMinutes + minutes
            )
            .apply()
    }

    fun getWeeklyWorkoutCount(
        context: Context
    ): Int {

        val history =
            WorkoutHistoryManager.getHistory(
                context
            )

        if (history.isEmpty()) {
            return 0
        }

        val currentWeek =
            getCurrentWeek()

        var count = 0

        for (workout in history) {

            if (
                isDateInCurrentWeek(
                    workout.date,
                    currentWeek
                )
            ) {
                count++
            }
        }

        return count
    }

    private fun getCurrentWeek(): Int {

        val calendar =
            Calendar.getInstance()

        return calendar.get(
            Calendar.WEEK_OF_YEAR
        )
    }

    private fun isDateInCurrentWeek(
        dateString: String,
        currentWeek: Int
    ): Boolean {

        return try {

            val formatter =
                SimpleDateFormat(
                    "MMM dd, yyyy",
                    Locale.getDefault()
                )

            val date =
                formatter.parse(
                    dateString
                ) ?: return false

            val workoutCalendar =
                Calendar.getInstance()

            workoutCalendar.time =
                date

            val currentCalendar =
                Calendar.getInstance()

            val workoutWeek =
                workoutCalendar.get(
                    Calendar.WEEK_OF_YEAR
                )

            val workoutYear =
                workoutCalendar.get(
                    Calendar.YEAR
                )

            val currentYear =
                currentCalendar.get(
                    Calendar.YEAR
                )

            workoutWeek == currentWeek &&
                    workoutYear == currentYear

        } catch (
            exception: Exception
        ) {

            false
        }
    }

    private fun getPreferences(
        context: Context
    ) = context.getSharedPreferences(
        PREF_NAME,
        Context.MODE_PRIVATE
    )
}

