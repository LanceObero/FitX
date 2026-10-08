package com.example.fitx.utils

import android.content.Context

object FitnessGoalManager {

    private const val PREF_NAME = "fitx_settings"
    private const val FITNESS_GOAL = "fitness_goal"

    fun saveGoal(
        context: Context,
        goal: String
    ) {
        context.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )
            .edit()
            .putString(
                FITNESS_GOAL,
                goal
            )
            .apply()
    }

    fun getGoal(
        context: Context
    ): String {

        return context.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )
            .getString(
                FITNESS_GOAL,
                "Build Strength"
            )
            ?: "Build Strength"
    }
}

