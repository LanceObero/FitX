package com.example.fitx.ui.workout

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fitx.R

class BodyPartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_body_part
        )

        val category =
            intent.getStringExtra(
                "WORKOUT_CATEGORY"
            )

        val btnBack =
            findViewById<Button>(
                R.id.btnBack
            )

        btnBack.setOnClickListener {
            finish()
        }

        val chest =
            findViewById<TextView>(
                R.id.bodyChest
            )

        val arms =
            findViewById<TextView>(
                R.id.bodyArms
            )

        val legs =
            findViewById<TextView>(
                R.id.bodyLegs
            )

        val core =
            findViewById<TextView>(
                R.id.bodyCore
            )

        val shoulders =
            findViewById<TextView>(
                R.id.bodyShoulders
            )

        val back =
            findViewById<TextView>(
                R.id.bodyBack
            )

        val fullBody =
            findViewById<TextView>(
                R.id.bodyFullBody
            )

        chest.setOnClickListener {
            openWorkout("Chest", category)
        }

        arms.setOnClickListener {
            openWorkout("Arms", category)
        }

        legs.setOnClickListener {
            openWorkout("Legs", category)
        }

        core.setOnClickListener {
            openWorkout("Core", category)
        }

        shoulders.setOnClickListener {
            openWorkout("Shoulders", category)
        }

        back.setOnClickListener {
            openWorkout("Back", category)
        }

        fullBody.setOnClickListener {
            openWorkout("Full Body", category)
        }
    }

    private fun openWorkout(
        bodyPart: String,
        category: String?
    ) {

        val intent =
            Intent(
                this,
                WorkoutActivity::class.java
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