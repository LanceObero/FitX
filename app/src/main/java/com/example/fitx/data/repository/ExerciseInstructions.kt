package com.example.fitx.data.repository

data class ExerciseGuide(
    val instructions: String,
    val tip: String
)

object ExerciseInstructions {

    fun getGuide(
        exerciseName: String
    ): ExerciseGuide {

        return when (exerciseName) {

            "Push-ups" ->
                ExerciseGuide(
                    "Start in a high plank position. " +
                            "Keep your body straight and lower your chest " +
                            "toward the floor. Push back up to the starting position.",
                    "Keep your core tight and avoid letting your hips drop."
                )

            "Diamond Push-ups" ->
                ExerciseGuide(
                    "Place your hands close together under your chest " +
                            "with your thumbs and index fingers forming a diamond. " +
                            "Lower your body and push back up.",
                    "Move slowly and keep your elbows controlled."
                )

            "Wide Push-ups" ->
                ExerciseGuide(
                    "Place your hands wider than shoulder-width apart. " +
                            "Lower your chest toward the floor and push back up.",
                    "Keep your body straight throughout the movement."
                )

            "Incline Push-ups" ->
                ExerciseGuide(
                    "Place your hands on a stable elevated surface. " +
                            "Keep your body straight and lower your chest " +
                            "toward the surface before pushing back up.",
                    "Use a stable surface that will not move."
                )

            "Squats" ->
                ExerciseGuide(
                    "Stand with your feet about shoulder-width apart. " +
                            "Bend your knees and push your hips backward " +
                            "as if sitting in a chair. Return to standing.",
                    "Keep your knees aligned with your feet."
                )

            "Lunges" ->
                ExerciseGuide(
                    "Stand upright and step one foot forward. " +
                            "Lower your hips until both knees are bent, " +
                            "then push through the front foot to return.",
                    "Keep your upper body upright."
                )

            "Glute Bridges" ->
                ExerciseGuide(
                    "Lie on your back with your knees bent and feet flat. " +
                            "Lift your hips toward the ceiling and squeeze your glutes. " +
                            "Lower your hips with control.",
                    "Avoid overextending your lower back."
                )

            "Calf Raises" ->
                ExerciseGuide(
                    "Stand upright with your feet flat. " +
                            "Raise your heels until you are on your toes, " +
                            "then slowly lower them back down.",
                    "Use slow and controlled movements."
                )

            "Plank" ->
                ExerciseGuide(
                    "Place your forearms on the floor and extend your legs behind you. " +
                            "Keep your body in a straight line while supporting yourself " +
                            "with your forearms and toes.",
                    "Keep your core tight and avoid raising or dropping your hips."
                )

            "Crunches" ->
                ExerciseGuide(
                    "Lie on your back with your knees bent. " +
                            "Place your hands lightly behind your head. " +
                            "Lift your shoulders toward your knees and slowly lower back down.",
                    "Do not pull on your neck."
                )

            "Leg Raises" ->
                ExerciseGuide(
                    "Lie flat on your back with your legs extended. " +
                            "Keep your legs together and raise them upward. " +
                            "Lower them slowly without swinging.",
                    "Keep your lower back controlled against the floor."
                )

            "Mountain Climbers" ->
                ExerciseGuide(
                    "Start in a high plank position. " +
                            "Bring one knee toward your chest, then switch legs quickly " +
                            "while keeping your hands on the floor.",
                    "Keep your shoulders above your hands."
                )

            "Jumping Jacks" ->
                ExerciseGuide(
                    "Stand with your feet together and arms at your sides. " +
                            "Jump while moving your feet apart and raising your arms overhead. " +
                            "Jump back to the starting position.",
                    "Land softly and keep your movements controlled."
                )

            "High Knees" ->
                ExerciseGuide(
                    "Stand upright and run in place while driving your knees upward " +
                            "toward your waist.",
                    "Stay light on your feet and maintain a steady rhythm."
                )

            "Burpees" ->
                ExerciseGuide(
                    "Start standing. Squat down and place your hands on the floor. " +
                            "Move your feet back into a plank, return them forward, " +
                            "then stand up and jump.",
                    "Perform the movement at a controlled pace."
                )

            "Tricep Dips" ->
                ExerciseGuide(
                    "Sit near the edge of a stable surface and place your hands beside your hips. " +
                            "Move your hips forward and bend your elbows to lower your body. " +
                            "Push back up.",
                    "Use a stable surface and keep your shoulders controlled."
                )

            "Arm Circles" ->
                ExerciseGuide(
                    "Stand upright with your arms extended to the sides. " +
                            "Make small controlled circles with your arms.",
                    "Keep your shoulders relaxed."
                )

            "Plank Shoulder Taps" ->
                ExerciseGuide(
                    "Start in a high plank position. " +
                            "Lift one hand and tap the opposite shoulder. " +
                            "Return the hand to the floor and alternate sides.",
                    "Keep your hips as stable as possible."
                )

            "Shoulder Taps" ->
                ExerciseGuide(
                    "Start in a high plank position. " +
                            "Tap one shoulder with the opposite hand, " +
                            "then alternate sides.",
                    "Avoid rotating your hips."
                )

            "Pike Push-ups" ->
                ExerciseGuide(
                    "Start in a downward-dog-like position with your hips raised. " +
                            "Bend your elbows and lower your head toward the floor, " +
                            "then push back up.",
                    "Keep your movement controlled."
                )

            "Wall Push-ups" ->
                ExerciseGuide(
                    "Stand facing a wall and place your hands against it. " +
                            "Bend your elbows to bring your chest toward the wall, " +
                            "then push away.",
                    "Keep your body straight."
                )

            "Superman" ->
                ExerciseGuide(
                    "Lie face down with your arms extended forward. " +
                            "Lift your arms and legs slightly off the floor, " +
                            "then lower them with control.",
                    "Avoid excessive arching of your lower back."
                )

            "Bird Dog" ->
                ExerciseGuide(
                    "Start on your hands and knees. " +
                            "Extend one arm and the opposite leg at the same time. " +
                            "Return to the starting position and switch sides.",
                    "Keep your hips level."
                )

            "Reverse Snow Angel" ->
                ExerciseGuide(
                    "Lie face down with your arms extended overhead. " +
                            "Move your arms outward and toward your hips " +
                            "while keeping them slightly off the floor.",
                    "Move slowly and keep your shoulders controlled."
                )

            "Swimmer" ->
                ExerciseGuide(
                    "Lie face down with your arms extended forward. " +
                            "Alternate lifting the opposite arm and leg " +
                            "in a controlled swimming motion.",
                    "Keep the movement small and controlled."
                )

            else ->
                ExerciseGuide(
                    "Perform the exercise using a controlled and comfortable range of motion.",
                    "Stop if you experience pain or unusual discomfort."
                )
        }
    }
}