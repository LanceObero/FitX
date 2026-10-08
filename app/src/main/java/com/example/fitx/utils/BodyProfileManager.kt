package com.example.fitx.utils

import android.content.Context

object BodyProfileManager {

    private const val PREF_NAME = "fitx_body_profile"

    private const val AGE = "age"
    private const val HEIGHT = "height"
    private const val WEIGHT = "weight"

    fun saveProfile(
        context: Context,
        age: Int,
        heightCm: Float,
        weightKg: Float
    ) {
        context
            .getSharedPreferences(
                PREF_NAME,
                Context.MODE_PRIVATE
            )
            .edit()
            .putInt(AGE, age)
            .putFloat(HEIGHT, heightCm)
            .putFloat(WEIGHT, weightKg)
            .apply()
    }

    fun getAge(context: Context): Int {
        return context
            .getSharedPreferences(
                PREF_NAME,
                Context.MODE_PRIVATE
            )
            .getInt(AGE, 0)
    }

    fun getHeight(context: Context): Float {
        return context
            .getSharedPreferences(
                PREF_NAME,
                Context.MODE_PRIVATE
            )
            .getFloat(HEIGHT, 0f)
    }

    fun getWeight(context: Context): Float {
        return context
            .getSharedPreferences(
                PREF_NAME,
                Context.MODE_PRIVATE
            )
            .getFloat(WEIGHT, 0f)
    }

    fun hasProfile(context: Context): Boolean {
        return getAge(context) > 0 &&
                getHeight(context) > 0f &&
                getWeight(context) > 0f
    }

    fun calculateBmi(
        heightCm: Float,
        weightKg: Float
    ): Float {

        if (heightCm <= 0f || weightKg <= 0f) {
            return 0f
        }

        val heightMeters =
            heightCm / 100f

        return weightKg /
                (heightMeters * heightMeters)
    }

    fun getBmiCategory(bmi: Float): String {

        return when {
            bmi <= 0f ->
                "Not available"

            bmi < 18.5f ->
                "Underweight"

            bmi < 25f ->
                "Normal range"

            bmi < 30f ->
                "Overweight"

            else ->
                "Obesity"
        }
    }
}
