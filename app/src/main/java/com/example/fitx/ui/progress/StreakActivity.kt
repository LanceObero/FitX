package com.example.fitx.ui.progress

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fitx.R
import com.example.fitx.utils.StreakManager

class StreakActivity : AppCompatActivity() {

    private lateinit var streakContainer: LinearLayout
    private lateinit var tvCurrentStreak: TextView

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_streak
        )

        streakContainer =
            findViewById(
                R.id.streakContainer
            )

        tvCurrentStreak =
            findViewById(
                R.id.tvCurrentStreak
            )

        val btnBack =
            findViewById<Button>(
                R.id.btnBack
            )

        btnBack.setOnClickListener {
            finish()
        }

        loadStreak()
    }

    override fun onResume() {
        super.onResume()

        if (::streakContainer.isInitialized) {
            loadStreak()
        }
    }

    private fun loadStreak() {

        val streak =
            StreakManager.getStreak(
                this
            )

        tvCurrentStreak.text =
            "🔥 $streak DAY STREAK"

        streakContainer.removeAllViews()

        // Show the last 14 days.
        for (offset in -13..0) {

            val date =
                StreakManager.getDateForOffset(
                    offset
                )

            val hasWorkout =
                StreakManager.hasWorkoutOnDate(
                    this,
                    date
                )

            val row =
                LinearLayout(this)

            row.orientation =
                LinearLayout.HORIZONTAL

            row.gravity =
                android.view.Gravity.CENTER_VERTICAL

            row.setPadding(
                18,
                18,
                18,
                18
            )

            row.setBackgroundResource(
                R.drawable.bg_card
            )

            val dayText =
                TextView(this)

            dayText.text =
                if (offset == 0) {
                    "TODAY"
                } else {
                    date
                }

            dayText.textSize =
                15f

            dayText.setTextColor(
                getColor(
                    R.color.fitx_white
                )
            )

            dayText.layoutParams =
                LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1f
                )

            val statusText =
                TextView(this)

            statusText.text =
                if (hasWorkout) {
                    "✓ WORKOUT"
                } else {
                    "REST"
                }

            statusText.textSize =
                14f

            statusText.setTypeface(
                null,
                android.graphics.Typeface.BOLD
            )

            statusText.setTextColor(
                if (hasWorkout) {
                    getColor(
                        R.color.fitx_primary
                    )
                } else {
                    getColor(
                        R.color.fitx_gray
                    )
                }
            )

            row.addView(dayText)
            row.addView(statusText)

            val params =
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

            params.setMargins(
                0,
                0,
                0,
                10
            )

            streakContainer.addView(
                row,
                params
            )
        }
    }
}

