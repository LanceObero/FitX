package com.example.fitx.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fitx.R
import com.example.fitx.ui.profile.ProfileActivity
import com.example.fitx.ui.progress.ProgressActivity
import com.example.fitx.ui.workout.BodyPartActivity
import com.example.fitx.utils.FitnessGoalManager
import com.example.fitx.utils.FitnessLevelManager
import com.example.fitx.utils.ProgressManager
import com.example.fitx.utils.StreakManager

class HomeActivity : AppCompatActivity() {

    private lateinit var tvHomeWorkoutCount: TextView
    private lateinit var tvHomeCalories: TextView
    private lateinit var tvHomeMinutes: TextView

    private lateinit var tvFitnessGoal: TextView
    private lateinit var tvFitnessLevel: TextView
    private lateinit var tvRecommendedWorkout: TextView

    private lateinit var tvStreak: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_home
        )

        tvHomeWorkoutCount =
            findViewById(
                R.id.tvHomeWorkoutCount
            )

        tvHomeCalories =
            findViewById(
                R.id.tvHomeCalories
            )

        tvHomeMinutes =
            findViewById(
                R.id.tvHomeMinutes
            )

        tvFitnessGoal =
            findViewById(
                R.id.tvFitnessGoal
            )

        tvFitnessLevel =
            findViewById(
                R.id.tvFitnessLevel
            )

        tvRecommendedWorkout =
            findViewById(
                R.id.tvRecommendedWorkout
            )

        tvStreak =
            findViewById(
                R.id.tvStreak
            )

        val btnStartWorkout =
            findViewById<Button>(
                R.id.btnStartWorkout
            )

        btnStartWorkout.setOnClickListener {
            openWorkout()
        }

        val categoryStrength =
            findViewById<TextView>(
                R.id.categoryStrength
            )

        val categoryCardio =
            findViewById<TextView>(
                R.id.categoryCardio
            )

        val categoryAbs =
            findViewById<TextView>(
                R.id.categoryAbs
            )

        categoryStrength.setOnClickListener {
            openWorkout("Strength")
        }

        categoryCardio.setOnClickListener {
            openWorkout("Cardio")
        }

        categoryAbs.setOnClickListener {
            openWorkout("Abs")
        }

        val navHome =
            findViewById<TextView>(
                R.id.navHome
            )

        val navWorkout =
            findViewById<TextView>(
                R.id.navWorkout
            )

        val navProgress =
            findViewById<TextView>(
                R.id.navProgress
            )

        val navProfile =
            findViewById<TextView>(
                R.id.navProfile
            )

        navHome.setOnClickListener {
            // Already on Home
        }

        navWorkout.setOnClickListener {
            openWorkout()
        }

        navProgress.setOnClickListener {
            val intent =
                Intent(
                    this,
                    ProgressActivity::class.java
                )

            startActivity(intent)
        }

        navProfile.setOnClickListener {
            val intent =
                Intent(
                    this,
                    ProfileActivity::class.java
                )

            startActivity(intent)
        }

        loadProgress()
        loadPersonalization()
        loadStreak()
    }

    override fun onResume() {
        super.onResume()

        if (::tvHomeWorkoutCount.isInitialized) {
            loadProgress()
        }

        if (::tvFitnessGoal.isInitialized) {
            loadPersonalization()
        }

        if (::tvStreak.isInitialized) {
            loadStreak()
        }
    }

    private fun loadProgress() {

        val workoutCount =
            ProgressManager.getWorkoutCount(
                this
            )

        val calories =
            ProgressManager.getCalories(
                this
            )

        val minutes =
            ProgressManager.getMinutes(
                this
            )

        tvHomeWorkoutCount.text =
            workoutCount.toString()

        tvHomeCalories.text =
            calories.toString()

        tvHomeMinutes.text =
            minutes.toString()
    }

    private fun loadPersonalization() {

        val goal =
            FitnessGoalManager.getGoal(
                this
            )

        val level =
            FitnessLevelManager.getLevel(
                this
            )

        tvFitnessGoal.text =
            goal

        tvFitnessLevel.text =
            level

        tvRecommendedWorkout.text =
            getRecommendedWorkout(
                goal
            )
    }

    private fun loadStreak() {

        val streak =
            StreakManager.getStreak(
                this
            )

        tvStreak.text =
            if (streak <= 0) {
                "0 DAY STREAK"
            } else {
                "$streak DAY STREAK"
            }
    }

    private fun getRecommendedWorkout(
        goal: String
    ): String {

        return when (goal) {

            "Build Strength" ->
                "Strength Training"

            "Lose Weight" ->
                "Full Body Burn"

            "Improve Endurance" ->
                "Endurance Training"

            else ->
                "Full Body Fitness"
        }
    }

    private fun openWorkout(
        category: String? = null
    ) {

        val intent =
            Intent(
                this,
                BodyPartActivity::class.java
            )

        if (category != null) {

            intent.putExtra(
                "WORKOUT_CATEGORY",
                category
            )
        }

        startActivity(intent)
    }
}

