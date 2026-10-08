package com.example.fitx.utils

import android.content.Context
import org.json.JSONArray
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object StreakManager {

    private const val PREF_NAME =
        "fitx_streak"

    private const val CURRENT_STREAK =
        "current_streak"

    private const val LAST_WORKOUT_DATE =
        "last_workout_date"

    private const val WORKOUT_DATES =
        "workout_dates"

    fun getStreak(
        context: Context
    ): Int {

        return context.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )
            .getInt(
                CURRENT_STREAK,
                0
            )
    }

    fun recordWorkout(
        context: Context
    ) {

        val preferences =
            context.getSharedPreferences(
                PREF_NAME,
                Context.MODE_PRIVATE
            )

        val today =
            getToday()

        val lastWorkout =
            preferences.getString(
                LAST_WORKOUT_DATE,
                null
            )

        val currentStreak =
            preferences.getInt(
                CURRENT_STREAK,
                0
            )

        // Save today's workout date.
        saveWorkoutDate(
            context,
            today
        )

        // Workout already recorded today.
        if (lastWorkout == today) {
            return
        }

        // First workout.
        if (lastWorkout == null) {

            preferences.edit()
                .putInt(
                    CURRENT_STREAK,
                    1
                )
                .putString(
                    LAST_WORKOUT_DATE,
                    today
                )
                .apply()

            return
        }

        val yesterday =
            getYesterday()

        // Continue streak.
        if (lastWorkout == yesterday) {

            preferences.edit()
                .putInt(
                    CURRENT_STREAK,
                    currentStreak + 1
                )
                .putString(
                    LAST_WORKOUT_DATE,
                    today
                )
                .apply()

        } else {

            // Streak broken.
            preferences.edit()
                .putInt(
                    CURRENT_STREAK,
                    1
                )
                .putString(
                    LAST_WORKOUT_DATE,
                    today
                )
                .apply()
        }
    }

    fun getWorkoutDates(
        context: Context
    ): List<String> {

        val preferences =
            context.getSharedPreferences(
                PREF_NAME,
                Context.MODE_PRIVATE
            )

        val json =
            preferences.getString(
                WORKOUT_DATES,
                null
            )

        if (json.isNullOrEmpty()) {
            return emptyList()
        }

        val jsonArray =
            JSONArray(json)

        val dates =
            mutableListOf<String>()

        for (i in 0 until jsonArray.length()) {

            dates.add(
                jsonArray.getString(i)
            )
        }

        return dates
    }

    private fun saveWorkoutDate(
        context: Context,
        date: String
    ) {

        val dates =
            getWorkoutDates(
                context
            ).toMutableList()

        // Do not save duplicate dates.
        if (dates.contains(date)) {
            return
        }

        dates.add(date)

        val jsonArray =
            JSONArray()

        for (item in dates) {
            jsonArray.put(item)
        }

        context.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )
            .edit()
            .putString(
                WORKOUT_DATES,
                jsonArray.toString()
            )
            .apply()
    }

    fun hasWorkoutOnDate(
        context: Context,
        date: String
    ): Boolean {

        return getWorkoutDates(
            context
        ).contains(date)
    }

    fun getDateForOffset(
        offset: Int
    ): String {

        val calendar =
            Calendar.getInstance()

        calendar.add(
            Calendar.DAY_OF_YEAR,
            offset
        )

        val formatter =
            SimpleDateFormat(
                "yyyy-MM-dd",
                Locale.getDefault()
            )

        return formatter.format(
            calendar.time
        )
    }

    fun getDisplayDate(
        date: String
    ): String {

        return try {

            val inputFormatter =
                SimpleDateFormat(
                    "yyyy-MM-dd",
                    Locale.getDefault()
                )

            val outputFormatter =
                SimpleDateFormat(
                    "MMM dd, yyyy",
                    Locale.getDefault()
                )

            val parsedDate =
                inputFormatter.parse(date)

            outputFormatter.format(
                parsedDate ?: Date()
            )

        } catch (
            exception: Exception
        ) {

            date
        }
    }

    private fun getToday(): String {

        val formatter =
            SimpleDateFormat(
                "yyyy-MM-dd",
                Locale.getDefault()
            )

        return formatter.format(
            Date()
        )
    }

    private fun getYesterday(): String {

        return getDateForOffset(-1)
    }
}

