package com.example.fitx.ui.workout

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fitx.R
import com.example.fitx.data.model.Exercise
import com.example.fitx.data.repository.ExerciseInstructions
import com.example.fitx.data.repository.WorkoutData
import com.example.fitx.utils.FitnessGoalManager
import com.example.fitx.utils.FitnessLevelManager

class ActiveWorkoutActivity : AppCompatActivity() {

    private var currentExercise = 0
    private var isResting = false
    private var isPreparing = false
    private var isExerciseComplete = false

    private var currentReps = 0
    private var targetReps = 0

    private lateinit var exercises: List<Exercise>
    private lateinit var fitnessLevel: String

    private lateinit var tvExerciseNumber: TextView
    private lateinit var tvExerciseName: TextView
    private lateinit var tvExerciseTarget: TextView
    private lateinit var tvExerciseInstructions: TextView
    private lateinit var tvExerciseTip: TextView
    private lateinit var tvTimer: TextView

    private lateinit var ivExerciseDemo: ImageView

    private lateinit var repCounterContainer: View
    private lateinit var tvRepCount: TextView
    private lateinit var btnMinusRep: Button
    private lateinit var btnPlusRep: Button

    private lateinit var btnNext: Button

    private var countDownTimer: CountDownTimer? = null

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_active_workout
        )

        val bodyPart =
            intent.getStringExtra(
                "BODY_PART"
            ) ?: "Full Body"

        val category =
            intent.getStringExtra(
                "WORKOUT_CATEGORY"
            )

        fitnessLevel =
            FitnessLevelManager.getLevel(this)

        val fitnessGoal =
            FitnessGoalManager.getGoal(this)

        exercises =
            getExercises(
                bodyPart,
                fitnessGoal,
                category
            )

        tvExerciseNumber =
            findViewById(
                R.id.tvExerciseNumber
            )

        tvExerciseName =
            findViewById(
                R.id.tvExerciseName
            )

        tvExerciseTarget =
            findViewById(
                R.id.tvExerciseTarget
            )

        tvExerciseInstructions =
            findViewById(
                R.id.tvExerciseInstructions
            )

        tvExerciseTip =
            findViewById(
                R.id.tvExerciseTip
            )

        tvTimer =
            findViewById(
                R.id.tvTimer
            )

        ivExerciseDemo =
            findViewById(
                R.id.ivExerciseDemo
            )

        repCounterContainer =
            findViewById(
                R.id.repCounterContainer
            )

        tvRepCount =
            findViewById(
                R.id.tvRepCount
            )

        btnMinusRep =
            findViewById(
                R.id.btnMinusRep
            )

        btnPlusRep =
            findViewById(
                R.id.btnPlusRep
            )

        btnNext =
            findViewById(
                R.id.btnNext
            )

        val btnBack =
            findViewById<Button>(
                R.id.btnBack
            )

        /*
         * BACK button
         */
        btnBack.setOnClickListener {

            countDownTimer?.cancel()

            finish()
        }

        /*
         * MINUS REP button
         */
        btnMinusRep.setOnClickListener {

            if (
                isPreparing ||
                isResting ||
                isExerciseComplete
            ) {
                return@setOnClickListener
            }

            if (currentReps > 0) {

                currentReps--

                updateRepCounter()
            }
        }

        /*
         * PLUS REP button
         */
        btnPlusRep.setOnClickListener {

            if (
                isPreparing ||
                isResting ||
                isExerciseComplete
            ) {
                return@setOnClickListener
            }

            if (
                currentReps <
                targetReps
            ) {

                currentReps++

                updateRepCounter()

                if (
                    currentReps >=
                    targetReps
                ) {

                    completeCurrentExercise()
                }
            }
        }

        /*
         * NEXT / COMPLETE button
         */
        btnNext.setOnClickListener {

            /*
             * Do nothing while preparation
             * countdown is running.
             */
            if (isPreparing) {
                return@setOnClickListener
            }

            /*
             * During rest, skip rest.
             */
            if (isResting) {

                countDownTimer?.cancel()

                isResting = false

                currentExercise++

                if (
                    currentExercise >=
                    exercises.size
                ) {

                    finishWorkout(
                        bodyPart,
                        category
                    )

                } else {

                    showExercise()
                }

                return@setOnClickListener
            }

            /*
             * If exercise has already been completed,
             * move to the next exercise.
             */
            if (isExerciseComplete) {

                countDownTimer?.cancel()

                if (
                    currentExercise ==
                    exercises.size - 1
                ) {

                    finishWorkout(
                        bodyPart,
                        category
                    )

                } else {

                    startRest()
                }

                return@setOnClickListener
            }

            /*
             * Rep-based exercise:
             * user can only finish after reaching
             * the target.
             */
            if (targetReps > 0) {

                if (
                    currentReps >=
                    targetReps
                ) {

                    completeCurrentExercise()

                } else {

                    tvTimer.text =
                        "COMPLETE $targetReps REPS"
                }

                return@setOnClickListener
            }

            /*
             * Time-based exercise.
             */
            countDownTimer?.cancel()

            if (
                currentExercise ==
                exercises.size - 1
            ) {

                finishWorkout(
                    bodyPart,
                    category
                )

            } else {

                startRest()
            }
        }

        showExercise()
    }

    private fun showExercise() {

        countDownTimer?.cancel()

        isResting = false
        isPreparing = false
        isExerciseComplete = false

        currentReps = 0
        targetReps = 0

        val exercise =
            exercises[currentExercise]

        tvExerciseNumber.text =
            "EXERCISE ${currentExercise + 1} / ${exercises.size}"

        tvExerciseName.text =
            exercise.name

        /*
         * Exercise image.
         */
        ivExerciseDemo.setImageResource(
            getExerciseImage(
                exercise.name
            )
        )

        /*
         * Target.
         */
        val target =
            exercise.getTarget(
                fitnessLevel
            )

        tvExerciseTarget.text =
            target

        /*
         * Instructions.
         */
        val guide =
            ExerciseInstructions.getGuide(
                exercise.name
            )

        tvExerciseInstructions.text =
            guide.instructions

        tvExerciseTip.text =
            "TIP: ${guide.tip}"

        /*
         * Determine if this is a
         * repetition-based exercise.
         */
        targetReps =
            extractReps(
                target
            )

        if (targetReps > 0) {

            /*
             * Show rep counter.
             */
            repCounterContainer.visibility =
                View.VISIBLE

            tvRepCount.text =
                "0"

            tvTimer.text =
                "COMPLETE $targetReps REPS"

            btnNext.text =
                "COMPLETE EXERCISE"

            btnPlusRep.isEnabled = true
            btnMinusRep.isEnabled = true

        } else {

            /*
             * Hide rep counter for
             * time-based exercises.
             */
            repCounterContainer.visibility =
                View.GONE

            btnNext.text =
                "GET READY"

            startPreparation()
        }
    }

    private fun extractReps(
        target: String
    ): Int {

        if (
            !target.contains(
                "reps",
                ignoreCase = true
            )
        ) {
            return 0
        }

        return target
            .replace(
                "reps",
                "",
                ignoreCase = true
            )
            .trim()
            .toIntOrNull()
            ?: 0
    }

    private fun updateRepCounter() {

        tvRepCount.text =
            currentReps.toString()

        if (
            currentReps >=
            targetReps
        ) {

            tvTimer.text =
                "TARGET REACHED!"

        } else {

            val remaining =
                targetReps -
                        currentReps

            tvTimer.text =
                "$remaining REPS LEFT"
        }
    }

    private fun completeCurrentExercise() {

        isExerciseComplete = true

        countDownTimer?.cancel()

        btnPlusRep.isEnabled = false
        btnMinusRep.isEnabled = false

        tvRepCount.text =
            targetReps.toString()

        tvTimer.text =
            "EXERCISE COMPLETE!"

        if (
            currentExercise ==
            exercises.size - 1
        ) {

            btnNext.text =
                "FINISH WORKOUT"

        } else {

            btnNext.text =
                "START REST"
        }
    }

    private fun startPreparation() {

        countDownTimer?.cancel()

        isPreparing = true

        tvTimer.text =
            "3"

        btnNext.text =
            "GET READY"

        countDownTimer =
            object : CountDownTimer(
                4_000,
                1_000
            ) {

                override fun onTick(
                    millisUntilFinished: Long
                ) {

                    val seconds =
                        (
                                millisUntilFinished /
                                        1000
                                ).toInt()

                    tvTimer.text =
                        seconds.toString()
                }

                override fun onFinish() {

                    isPreparing = false

                    tvTimer.text =
                        "START!"

                    btnNext.text =
                        if (
                            currentExercise ==
                            exercises.size - 1
                        ) {
                            "FINISH WORKOUT"
                        } else {
                            "COMPLETE EXERCISE"
                        }

                    val target =
                        exercises[
                            currentExercise
                        ].getTarget(
                            fitnessLevel
                        )

                    startTimerIfNeeded(
                        target
                    )
                }

            }.start()
    }

    private fun startTimerIfNeeded(
        target: String
    ) {

        if (
            !target.contains(
                "sec",
                ignoreCase = true
            )
        ) {

            return
        }

        val seconds =
            target
                .replace(
                    "sec",
                    "",
                    ignoreCase = true
                )
                .trim()
                .toLongOrNull()

        if (seconds == null) {

            tvTimer.text =
                "READY"

            return
        }

        tvTimer.text =
            formatTime(seconds)

        countDownTimer =
            object : CountDownTimer(
                seconds * 1000,
                1000
            ) {

                override fun onTick(
                    millisUntilFinished: Long
                ) {

                    val remaining =
                        millisUntilFinished / 1000

                    tvTimer.text =
                        formatTime(
                            remaining
                        )
                }

                override fun onFinish() {

                    isExerciseComplete = true

                    tvTimer.text =
                        "EXERCISE COMPLETE!"

                    if (
                        currentExercise ==
                        exercises.size - 1
                    ) {

                        btnNext.text =
                            "FINISH WORKOUT"

                    } else {

                        btnNext.text =
                            "START REST"
                    }
                }

            }.start()
    }

    private fun startRest() {

        isResting = true
        isPreparing = false
        isExerciseComplete = false

        countDownTimer?.cancel()

        repCounterContainer.visibility =
            View.GONE

        tvExerciseNumber.text =
            "REST PERIOD"

        tvExerciseName.text =
            "GET READY"

        /*
         * Hide previous exercise image.
         */
        ivExerciseDemo.setImageDrawable(
            null
        )

        tvExerciseTarget.text =
            "Next exercise coming up"

        tvExerciseInstructions.text =
            "Take a short break, breathe normally, " +
                    "and prepare for the next exercise."

        tvExerciseTip.text =
            "TIP: Stay hydrated and maintain good form."

        tvTimer.text =
            "00:15"

        btnNext.text =
            "SKIP REST"

        countDownTimer =
            object : CountDownTimer(
                15_000,
                1_000
            ) {

                override fun onTick(
                    millisUntilFinished: Long
                ) {

                    val remaining =
                        millisUntilFinished / 1000

                    tvTimer.text =
                        formatTime(
                            remaining
                        )
                }

                override fun onFinish() {

                    isResting = false

                    currentExercise++

                    if (
                        currentExercise >=
                        exercises.size
                    ) {

                        finishWorkout(
                            intent.getStringExtra(
                                "BODY_PART"
                            ) ?: "Full Body",
                            intent.getStringExtra(
                                "WORKOUT_CATEGORY"
                            )
                        )

                    } else {

                        showExercise()
                    }
                }

            }.start()
    }

    private fun finishWorkout(
        bodyPart: String,
        category: String?
    ) {

        countDownTimer?.cancel()

        val intent =
            Intent(
                this,
                WorkoutCompleteActivity::class.java
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

        finish()
    }

    private fun getExerciseImage(
        exerciseName: String
    ): Int {

        return when (exerciseName) {

            "Push-ups" ->
                R.drawable.push_ups

            "Diamond Push-ups" ->
                R.drawable.diamond_push_ups

            "Wide Push-ups" ->
                R.drawable.wide_push_ups

            "Incline Push-ups" ->
                R.drawable.incline_push_ups

            "Tricep Dips" ->
                R.drawable.tricep_dips

            "Arm Circles" ->
                R.drawable.arm_circles

            "Plank Shoulder Taps" ->
                R.drawable.plank_shoulder_taps

            "Squats" ->
                R.drawable.squats

            "Lunges" ->
                R.drawable.lunges

            "Glute Bridges" ->
                R.drawable.glute_bridges

            "Calf Raises" ->
                R.drawable.calf_raises

            "Plank" ->
                R.drawable.plank

            "Crunches" ->
                R.drawable.crunches

            "Leg Raises" ->
                R.drawable.leg_raises

            "Mountain Climbers" ->
                R.drawable.mountain_climbers

            "Jumping Jacks" ->
                R.drawable.jumping_jacks

            "High Knees" ->
                R.drawable.high_knees

            "Burpees" ->
                R.drawable.burpees

            "Pike Push-ups" ->
                R.drawable.pike_push_ups

            "Shoulder Taps" ->
                R.drawable.shoulder_taps

            "Wall Push-ups" ->
                R.drawable.wall_push_ups

            "Superman" ->
                R.drawable.superman

            "Bird Dog" ->
                R.drawable.bird_dog

            "Reverse Snow Angel" ->
                R.drawable.reverse_snow_angel

            "Swimmer" ->
                R.drawable.swimmer

            else ->
                R.drawable.ic_launcher_foreground
        }
    }

    private fun formatTime(
        seconds: Long
    ): String {

        val minutes =
            seconds / 60

        val remainingSeconds =
            seconds % 60

        return String.format(
            "%02d:%02d",
            minutes,
            remainingSeconds
        )
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

    override fun onDestroy() {

        countDownTimer?.cancel()

        super.onDestroy()
    }
}