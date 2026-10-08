package com.example.fitx.utils

import android.content.Context

object FitnessLevelManager {

    private const val PREF_NAME = "fitx_settings"
    private const val FITNESS_LEVEL = "fitness_level"

    fun saveLevel(
        context: Context,
        level: String
    ) {
        context.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )
            .edit()
            .putString(FITNESS_LEVEL, level)
            .apply()
    }

    fun getLevel(
        context: Context
    ): String {

        return context.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )
            .getString(
                FITNESS_LEVEL,
                "Beginner"
            )
            ?: "Beginner"
    }
}

