package com.example.fitx.ui.workout

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fitx.R
import com.example.fitx.data.model.Exercise
import com.example.fitx.data.repository.WorkoutData
import com.example.fitx.utils.FitnessGoalManager
import com.example.fitx.utils.FitnessLevelManager

class WorkoutActivity : AppCompatActivity() {

    private lateinit var exercises: List<Exercise>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_workout)

        val bodyPart =
            intent.getStringExtra("BODY_PART")
                ?: "Full Body"

        val category =
            intent.getStringExtra("WORKOUT_CATEGORY")

        val level =
            FitnessLevelManager.getLevel(this)

        val goal =
            FitnessGoalManager.getGoal(this)

        val btnBack =
            findViewById<Button>(R.id.btnBack)

        btnBack.setOnClickListener {
            finish()
        }

        val tvWorkoutCategory =
            findViewById<TextView>(
                R.id.tvWorkoutCategory
            )

        val tvWorkoutName =
            findViewById<TextView>(
                R.id.tvWorkoutName
            )

        val tvWorkoutInfo =
            findViewById<TextView>(
                R.id.tvWorkoutInfo
            )

        tvWorkoutCategory.text =
            bodyPart.uppercase()

        tvWorkoutName.text =
            if (category != null) {
                "$category $bodyPart Workout"
            } else {
                "$bodyPart Home Workout"
            }

        tvWorkoutInfo.text =
            "20 minutes • $level • $goal • No Equipment"

        val exerciseContainer =
            findViewById<LinearLayout>(
                R.id.exerciseContainer
            )

        exercises =
            getExercises(
                bodyPart,
                goal,
                category
            )

        for ((index, exercise) in exercises.withIndex()) {

            val exerciseLayout =
                LinearLayout(this)

            exerciseLayout.orientation =
                LinearLayout.HORIZONTAL

            exerciseLayout.setPadding(
                16,
                16,
                16,
                16
            )

            exerciseLayout.setBackgroundResource(
                R.drawable.bg_card
            )

            // Exercise number

            val number =
                TextView(this)

            number.text =
                "${index + 1}"

            number.textSize =
                18f

            number.setTextColor(
                getColor(R.color.fitx_black)
            )

            number.gravity =
                Gravity.CENTER

            number.background =
                getDrawable(
                    R.drawable.bg_number
                )

            val numberParams =
                LinearLayout.LayoutParams(
                    45,
                    45
                )

            numberParams.setMargins(
                0,
                0,
                16,
                0
            )

            exerciseLayout.addView(
                number,
                numberParams
            )

            // Exercise name

            val name =
                TextView(this)

            name.text =
                exercise.name

            name.textSize =
                17f

            name.setTextColor(
                getColor(R.color.fitx_white)
            )

            name.layoutParams =
                LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1f
                )

            exerciseLayout.addView(name)

            // Target

            val target =
                TextView(this)

            target.text =
                exercise.getTarget(level)

            target.textSize =
                14f

            target.setTextColor(
                getColor(R.color.fitx_gray)
            )

            exerciseLayout.addView(target)

            val cardParams =
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

            cardParams.setMargins(
                0,
                0,
                0,
                12
            )

            exerciseContainer.addView(
                exerciseLayout,
                cardParams
            )
        }

        // Begin workout

        val btnBeginWorkout =
            findViewById<Button>(
                R.id.btnBeginWorkout
            )

        btnBeginWorkout.setOnClickListener {

            val intent =
                Intent(
                    this,
                    ActiveWorkoutActivity::class.java
                )

            intent.putExtra(
                "BODY_PART",
                bodyPart
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

    private fun getExercises(
        bodyPart: String,
        goal: String,
        category: String?
    ): List<Exercise> {

        return when (category) {

            "Abs" -> {

                listOf(
                    Exercise(
                        "Plank",
                        "20 sec",
                        "30 sec",
                        "60 sec"
                    ),
                    Exercise(
                        "Crunches",
                        "10 reps",
                        "15 reps",
                        "25 reps"
                    ),
                    Exercise(
                        "Leg Raises",
                        "6 reps",
                        "10 reps",
                        "15 reps"
                    ),
                    Exercise(
                        "Mountain Climbers",
                        "10 reps",
                        "20 reps",
                        "30 reps"
                    )
                )
            }

            "Cardio" -> {

                listOf(
                    Exercise(
                        "Jumping Jacks",
                        "20 sec",
                        "30 sec",
                        "45 sec"
                    ),
                    Exercise(
                        "Mountain Climbers",
                        "10 reps",
                        "20 reps",
                        "30 reps"
                    ),
                    Exercise(
                        "High Knees",
                        "20 sec",
                        "30 sec",
                        "45 sec"
                    ),
                    Exercise(
                        "Burpees",
                        "5 reps",
                        "10 reps",
                        "15 reps"
                    )
                )
            }

            "Strength" -> {

                WorkoutData.getExercises(
                    bodyPart,
                    "Build Strength"
                )
            }

            else -> {

                WorkoutData.getExercises(
                    bodyPart,
                    goal
                )
            }
        }
    }
}