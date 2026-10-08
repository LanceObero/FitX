package com.example.fitx.utils

import android.content.Context
import com.example.fitx.data.model.WorkoutHistory
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object WorkoutHistoryManager {

    private const val PREF_NAME =
        "fitx_workout_history"

    private const val HISTORY_KEY =
        "history"

    fun addWorkout(
        context: Context,
        category: String,
        bodyPart: String,
        calories: Int,
        minutes: Int
    ) {

        val preferences =
            context.getSharedPreferences(
                PREF_NAME,
                Context.MODE_PRIVATE
            )

        val history =
            getHistory(context).toMutableList()

        val dateFormat =
            SimpleDateFormat(
                "MMM dd, yyyy",
                Locale.getDefault()
            )

        val workout =
            WorkoutHistory(
                category = category,
                bodyPart = bodyPart,
                calories = calories,
                minutes = minutes,
                date = dateFormat.format(Date())
            )

        history.add(
            0,
            workout
        )

        val jsonArray =
            JSONArray()

        for (item in history) {

            val jsonObject =
                JSONObject()

            jsonObject.put(
                "category",
                item.category
            )

            jsonObject.put(
                "bodyPart",
                item.bodyPart
            )

            jsonObject.put(
                "calories",
                item.calories
            )

            jsonObject.put(
                "minutes",
                item.minutes
            )

            jsonObject.put(
                "date",
                item.date
            )

            jsonArray.put(
                jsonObject
            )
        }

        preferences.edit()
            .putString(
                HISTORY_KEY,
                jsonArray.toString()
            )
            .apply()
    }

    fun getHistory(
        context: Context
    ): List<WorkoutHistory> {

        val preferences =
            context.getSharedPreferences(
                PREF_NAME,
                Context.MODE_PRIVATE
            )

        val json =
            preferences.getString(
                HISTORY_KEY,
                null
            )

        if (json.isNullOrEmpty()) {
            return emptyList()
        }

        val jsonArray =
            JSONArray(json)

        val history =
            mutableListOf<WorkoutHistory>()

        for (i in 0 until jsonArray.length()) {

            val item =
                jsonArray.getJSONObject(i)

            val category =
                if (item.has("category")) {
                    item.getString("category")
                } else {
                    "Workout"
                }

            history.add(
                WorkoutHistory(
                    category = category,

                    bodyPart =
                        item.getString(
                            "bodyPart"
                        ),

                    calories =
                        item.getInt(
                            "calories"
                        ),

                    minutes =
                        item.getInt(
                            "minutes"
                        ),

                    date =
                        item.getString(
                            "date"
                        )
                )
            )
        }

        return history
    }
}