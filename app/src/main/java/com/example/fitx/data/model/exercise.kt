package com.example.fitx.data.model

data class Exercise(
    val name: String,
    val beginnerTarget: String,
    val intermediateTarget: String,
    val advancedTarget: String,
    val instructions: String = "",
    val tip: String = ""
) {

    fun getTarget(
        level: String
    ): String {

        return when (level) {

            "Intermediate" ->
                intermediateTarget

            "Advanced" ->
                advancedTarget

            else ->
                beginnerTarget
        }
    }
}