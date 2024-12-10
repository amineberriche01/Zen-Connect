package com.example.zenconnect

import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MoodTrackerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood_tracker)

        val moodGroup = findViewById<RadioGroup>(R.id.moodGroup)
        val saveButton = findViewById<Button>(R.id.saveMoodButton)

        saveButton.setOnClickListener {
            val selectedMoodId = moodGroup.checkedRadioButtonId
            if (selectedMoodId != -1) {
                val mood = when (selectedMoodId) {
                    R.id.radioHappy -> "Heureux"
                    R.id.radioNeutral -> "Neutre"
                    R.id.radioSad -> "Triste"
                    else -> "Inconnu"
                }
                // Sauvegarder l'humeur localement (par exemple, SharedPreferences)
                val sharedPreferences = getSharedPreferences("MoodPrefs", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("todayMood", mood)
                editor.apply()

                Toast.makeText(this, "Humeur enregistrée : $mood", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Veuillez sélectionner une humeur", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
