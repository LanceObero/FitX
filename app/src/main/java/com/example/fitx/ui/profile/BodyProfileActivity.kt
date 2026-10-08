package com.example.fitx.ui.profile

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fitx.R
import com.example.fitx.utils.BodyProfileManager

class BodyProfileActivity : AppCompatActivity() {

    private lateinit var etAge: EditText
    private lateinit var etHeight: EditText
    private lateinit var etWeight: EditText

    private lateinit var tvBmi: TextView
    private lateinit var tvBmiCategory: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_body_profile)

        etAge =
            findViewById(R.id.etAge)

        etHeight =
            findViewById(R.id.etHeight)

        etWeight =
            findViewById(R.id.etWeight)

        tvBmi =
            findViewById(R.id.tvBmi)

        tvBmiCategory =
            findViewById(R.id.tvBmiCategory)

        val btnBack =
            findViewById<Button>(R.id.btnBack)

        btnBack.setOnClickListener {
            finish()
        }

        val btnCalculate =
            findViewById<Button>(R.id.btnCalculate)

        btnCalculate.setOnClickListener {
            calculateBmi()
        }

        val btnSave =
            findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            saveProfile()
        }

        loadProfile()
    }

    private fun loadProfile() {

        val age =
            BodyProfileManager.getAge(this)

        val height =
            BodyProfileManager.getHeight(this)

        val weight =
            BodyProfileManager.getWeight(this)

        if (age > 0) {
            etAge.setText(age.toString())
        }

        if (height > 0f) {
            etHeight.setText(
                height.toString()
            )
        }

        if (weight > 0f) {
            etWeight.setText(
                weight.toString()
            )

            updateBmi(
                height,
                weight
            )
        }
    }

    private fun calculateBmi() {

        val heightText =
            etHeight.text.toString().trim()

        val weightText =
            etWeight.text.toString().trim()

        if (heightText.isEmpty()) {

            etHeight.error =
                "Enter your height"

            etHeight.requestFocus()

            return
        }

        if (weightText.isEmpty()) {

            etWeight.error =
                "Enter your weight"

            etWeight.requestFocus()

            return
        }

        val height =
            heightText.toFloatOrNull()

        val weight =
            weightText.toFloatOrNull()

        if (height == null || height <= 0f) {

            etHeight.error =
                "Enter a valid height"

            etHeight.requestFocus()

            return
        }

        if (weight == null || weight <= 0f) {

            etWeight.error =
                "Enter a valid weight"

            etWeight.requestFocus()

            return
        }

        updateBmi(
            height,
            weight
        )
    }

    private fun updateBmi(
        height: Float,
        weight: Float
    ) {

        val bmi =
            BodyProfileManager.calculateBmi(
                height,
                weight
            )

        val category =
            BodyProfileManager.getBmiCategory(
                bmi
            )

        tvBmi.text =
            String.format(
                "%.1f",
                bmi
            )

        tvBmiCategory.text =
            category
    }

    private fun saveProfile() {

        val ageText =
            etAge.text.toString().trim()

        val heightText =
            etHeight.text.toString().trim()

        val weightText =
            etWeight.text.toString().trim()

        if (ageText.isEmpty()) {

            etAge.error =
                "Enter your age"

            etAge.requestFocus()

            return
        }

        if (heightText.isEmpty()) {

            etHeight.error =
                "Enter your height"

            etHeight.requestFocus()

            return
        }

        if (weightText.isEmpty()) {

            etWeight.error =
                "Enter your weight"

            etWeight.requestFocus()

            return
        }

        val age =
            ageText.toIntOrNull()

        val height =
            heightText.toFloatOrNull()

        val weight =
            weightText.toFloatOrNull()

        if (age == null || age <= 0) {

            etAge.error =
                "Enter a valid age"

            etAge.requestFocus()

            return
        }

        if (height == null || height <= 0f) {

            etHeight.error =
                "Enter a valid height"

            etHeight.requestFocus()

            return
        }

        if (weight == null || weight <= 0f) {

            etWeight.error =
                "Enter a valid weight"

            etWeight.requestFocus()

            return
        }

        BodyProfileManager.saveProfile(
            this,
            age,
            height,
            weight
        )

        updateBmi(
            height,
            weight
        )

        Toast.makeText(
            this,
            "Body profile saved successfully!",
            Toast.LENGTH_SHORT
        ).show()
    }
}
