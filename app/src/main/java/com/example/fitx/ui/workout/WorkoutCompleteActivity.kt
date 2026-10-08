package com.example.fitx.ui.workout

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fitx.R
import com.example.fitx.ui.home.HomeActivity
import com.example.fitx.utils.FitnessGoalManager
import com.example.fitx.utils.FitnessLevelManager
import com.example.fitx.utils.ProgressManager
import com.example.fitx.utils.StreakManager
import com.example.fitx.utils.WorkoutHistoryManager

class WorkoutCompleteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_workout_complete
        )

        val bodyPart =
            intent.getStringExtra("BODY_PART")
                ?: "Full Body"

        val category =
            intent.getStringExtra(
                "WORKOUT_CATEGORY"
            )

        val fitnessGoal =
            FitnessGoalManager.getGoal(this)

        val fitnessLevel =
            FitnessLevelManager.getLevel(this)

        // Calculate workout results

        val minutes =
            getWorkoutMinutes(category)

        val calories =
            getWorkoutCalories(
                category,
                fitnessLevel
            )

        // Save progress

        ProgressManager.completeWorkout(
            this,
            calories,
            minutes
        )

        // Update workout streak

        StreakManager.recordWorkout(
            this
        )

        // Save workout history

        val historyCategory =
            category ?: "General"

        WorkoutHistoryManager.addWorkout(
            this,
            historyCategory,
            bodyPart,
            calories,
            minutes
        )

        // UI

        val tvWorkoutComplete =
            findViewById<TextView>(
                R.id.tvWorkoutComplete
            )

        val tvWorkoutSummary =
            findViewById<TextView>(
                R.id.tvWorkoutSummary
            )

        tvWorkoutComplete.text =
            "WORKOUT COMPLETE!"

        val workoutTitle =
            if (category != null) {
                "$category $bodyPart Workout"
            } else {
                "$bodyPart Workout"
            }

        tvWorkoutSummary.text =
            "$workoutTitle\n\n" +
                    "$minutes minutes • $calories calories\n" +
                    "$fitnessLevel • $fitnessGoal"

        val btnBackHome =
            findViewById<Button>(
                R.id.btnBackHome
            )

        btnBackHome.setOnClickListener {

            val intent =
                Intent(
                    this,
                    HomeActivity::class.java
                )

            intent.flags =
                Intent.FLAG_ACTIVITY_CLEAR_TOP or
                        Intent.FLAG_ACTIVITY_SINGLE_TOP

            startActivity(intent)

            finish()
        }
    }

    private fun getWorkoutMinutes(
        category: String?
    ): Int {

        return when (category) {

            "Cardio" ->
                25

            "Strength" ->
                20

            "Abs" ->
                15

            else ->
                20
        }
    }

    private fun getWorkoutCalories(
        category: String?,
        level: String
    ): Int {

        val baseCalories =
            when (category) {

                "Cardio" ->
                    150

                "Strength" ->
                    120

                "Abs" ->
                    90

                else ->
                    100
            }

        return when (level) {

            "Intermediate" ->
                baseCalories + 20

            "Advanced" ->
                baseCalories + 40

            else ->
                baseCalories
        }
    }
}

