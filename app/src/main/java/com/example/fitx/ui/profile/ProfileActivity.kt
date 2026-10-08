package com.example.fitx.ui.profile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fitx.MainActivity
import com.example.fitx.R
import com.example.fitx.utils.FitnessGoalManager
import com.example.fitx.utils.FitnessLevelManager

class ProfileActivity : AppCompatActivity() {

    private lateinit var radioFitnessGoal: RadioGroup
    private lateinit var radioFitnessLevel: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_profile)

        // Radio Groups
        radioFitnessGoal =
            findViewById(R.id.radioFitnessGoal)

        radioFitnessLevel =
            findViewById(R.id.radioFitnessLevel)

        // Load saved settings
        loadSavedSettings()

        // Back button
        val btnBack =
            findViewById<Button>(R.id.btnBack)

        btnBack.setOnClickListener {
            finish()
        }

        // Body Profile button
        val btnBodyProfile =
            findViewById<Button>(R.id.btnBodyProfile)

        btnBodyProfile.setOnClickListener {

            val intent =
                Intent(
                    this,
                    BodyProfileActivity::class.java
                )

            startActivity(intent)
        }

        // Save Profile button
        val btnSaveProfile =
            findViewById<Button>(R.id.btnSaveProfile)

        btnSaveProfile.setOnClickListener {
            saveFitnessSettings()
        }

        // Logout
        val btnLogout =
            findViewById<Button>(R.id.btnLogout)

        btnLogout.setOnClickListener {

            val intent =
                Intent(
                    this,
                    MainActivity::class.java
                )

            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)

            finish()
        }
    }

    override fun onResume() {
        super.onResume()

        if (::radioFitnessGoal.isInitialized &&
            ::radioFitnessLevel.isInitialized
        ) {
            loadSavedSettings()
        }
    }

    private fun loadSavedSettings() {

        val savedGoal =
            FitnessGoalManager.getGoal(this)

        val savedLevel =
            FitnessLevelManager.getLevel(this)

        selectGoal(savedGoal)
        selectLevel(savedLevel)
    }

    private fun selectGoal(goal: String) {

        for (i in 0 until radioFitnessGoal.childCount) {

            val radioButton =
                radioFitnessGoal.getChildAt(i)
                        as? RadioButton
                    ?: continue

            if (radioButton.text.toString() == goal) {

                radioButton.isChecked = true

                break
            }
        }
    }

    private fun selectLevel(level: String) {

        for (i in 0 until radioFitnessLevel.childCount) {

            val radioButton =
                radioFitnessLevel.getChildAt(i)
                        as? RadioButton
                    ?: continue

            if (radioButton.text.toString() == level) {

                radioButton.isChecked = true

                break
            }
        }
    }

    private fun saveFitnessSettings() {

        val selectedGoalId =
            radioFitnessGoal.checkedRadioButtonId

        val selectedLevelId =
            radioFitnessLevel.checkedRadioButtonId

        if (selectedGoalId == -1) {

            Toast.makeText(
                this,
                "Please select a fitness goal.",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        if (selectedLevelId == -1) {

            Toast.makeText(
                this,
                "Please select your fitness level.",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        val selectedGoal =
            findViewById<RadioButton>(
                selectedGoalId
            ).text.toString()

        val selectedLevel =
            findViewById<RadioButton>(
                selectedLevelId
            ).text.toString()

        FitnessGoalManager.saveGoal(
            this,
            selectedGoal
        )

        FitnessLevelManager.saveLevel(
            this,
            selectedLevel
        )

        Toast.makeText(
            this,
            "Profile settings saved!",
            Toast.LENGTH_SHORT
        ).show()
    }
}

