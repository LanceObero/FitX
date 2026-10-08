package com.example.fitx.ui.progress

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fitx.R
import com.example.fitx.utils.WorkoutHistoryManager

class WorkoutHistoryActivity : AppCompatActivity() {

    private lateinit var historyContainer: LinearLayout

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_workout_history
        )

        historyContainer =
            findViewById(
                R.id.historyContainer
            )

        val btnBack =
            findViewById<Button>(
                R.id.btnBack
            )

        btnBack.setOnClickListener {
            finish()
        }

        loadHistory()
    }

    override fun onResume() {
        super.onResume()

        if (::historyContainer.isInitialized) {
            loadHistory()
        }
    }

    private fun loadHistory() {

        historyContainer.removeAllViews()

        val history =
            WorkoutHistoryManager.getHistory(
                this
            )

        if (history.isEmpty()) {

            val emptyText =
                TextView(this)

            emptyText.text =
                "No workouts completed yet."

            emptyText.textSize =
                16f

            emptyText.setTextColor(
                getColor(
                    R.color.fitx_gray
                )
            )

            emptyText.setPadding(
                0,
                30,
                0,
                30
            )

            historyContainer.addView(
                emptyText
            )

            return
        }

        for (workout in history) {

            val card =
                LinearLayout(this)

            card.orientation =
                LinearLayout.VERTICAL

            card.setPadding(
                20,
                20,
                20,
                20
            )

            card.setBackgroundResource(
                R.drawable.bg_card
            )

            val title =
                TextView(this)

            title.text =
                "${workout.category.uppercase()} • " +
                        workout.bodyPart.uppercase()

            title.textSize =
                19f

            title.setTextColor(
                getColor(
                    R.color.fitx_white
                )
            )

            title.setTypeface(
                null,
                android.graphics.Typeface.BOLD
            )

            val date =
                TextView(this)

            date.text =
                workout.date

            date.textSize =
                14f

            date.setTextColor(
                getColor(
                    R.color.fitx_gray
                )
            )

            date.setPadding(
                0,
                6,
                0,
                0
            )

            val details =
                TextView(this)

            details.text =
                "${workout.calories} calories • " +
                        "${workout.minutes} minutes"

            details.textSize =
                14f

            details.setTextColor(
                getColor(
                    R.color.fitx_primary
                )
            )

            details.setPadding(
                0,
                10,
                0,
                0
            )

            card.addView(title)
            card.addView(date)
            card.addView(details)

            val params =
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

            params.setMargins(
                0,
                0,
                0,
                12
            )

            historyContainer.addView(
                card,
                params
            )
        }
    }
}