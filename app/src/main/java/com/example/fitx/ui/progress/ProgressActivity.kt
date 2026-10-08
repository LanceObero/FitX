package com.example.fitx.ui.progress

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fitx.R
import com.example.fitx.utils.ProgressManager
import com.example.fitx.utils.WorkoutHistoryManager

class ProgressActivity : AppCompatActivity() {

    private lateinit var tvWorkoutCount: TextView
    private lateinit var tvCalories: TextView
    private lateinit var tvMinutes: TextView
    private lateinit var tvWeeklyGoal: TextView
    private lateinit var tvRecentActivity: TextView
    private lateinit var progressWeeklyGoal: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_progress)

        tvWorkoutCount =
            findViewById(R.id.tvWorkoutCount)

        tvCalories =
            findViewById(R.id.tvCalories)

        tvMinutes =
            findViewById(R.id.tvMinutes)

        tvWeeklyGoal =
            findViewById(R.id.tvWeeklyGoal)

        tvRecentActivity =
            findViewById(R.id.tvRecentActivity)

        progressWeeklyGoal =
            findViewById(R.id.progressWeeklyGoal)

        val btnBack =
            findViewById<Button>(R.id.btnBack)

        btnBack.setOnClickListener {
            finish()
        }

        val btnViewHistory =
            findViewById<Button>(R.id.btnViewHistory)

        btnViewHistory.setOnClickListener {
            val intent =
                Intent(
                    this,
                    WorkoutHistoryActivity::class.java
                )

            startActivity(intent)
        }

        val btnViewStreak =
            findViewById<Button>(R.id.btnViewStreak)

        btnViewStreak.setOnClickListener {
            val intent =
                Intent(
                    this,
                    StreakActivity::class.java
                )

            startActivity(intent)
        }

        loadProgress()
    }

    override fun onResume() {
        super.onResume()

        if (::tvWorkoutCount.isInitialized) {
            loadProgress()
        }
    }

    private fun loadProgress() {

        // Lifetime statistics

        val workouts =
            ProgressManager.getWorkoutCount(this)

        val calories =
            ProgressManager.getCalories(this)

        val minutes =
            ProgressManager.getMinutes(this)

        tvWorkoutCount.text =
            workouts.toString()

        tvCalories.text =
            calories.toString()

        tvMinutes.text =
            minutes.toString()


        // Weekly goal

        progressWeeklyGoal.max = 5

        val weeklyWorkouts =
            ProgressManager.getWeeklyWorkoutCount(this)

        progressWeeklyGoal.progress =
            weeklyWorkouts.coerceAtMost(5)

        if (weeklyWorkouts >= 5) {

            tvWeeklyGoal.text =
                "Weekly goal completed! 🎉"

        } else {

            tvWeeklyGoal.text =
                "$weeklyWorkouts of 5 workouts completed this week"
        }


        // Recent activity

        loadRecentActivity()
    }

    private fun loadRecentActivity() {

        val history =
            WorkoutHistoryManager.getHistory(this)

        if (history.isEmpty()) {

            tvRecentActivity.text =
                "No workouts completed yet."

            return
        }

        val builder =
            StringBuilder()

        val recent =
            history.take(5)

        for (workout in recent) {

            builder.append(
                "${workout.date}     " +
                        "${workout.bodyPart} Workout"
            )

            builder.append("\n")

            builder.append(
                "${workout.calories} calories • " +
                        "${workout.minutes} minutes"
            )

            builder.append("\n\n")
        }

        tvRecentActivity.text =
            builder.toString().trim()
    }
}
