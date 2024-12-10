package com.example.zenconnect

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.zenconnect.data.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppointmentsActivity : AppCompatActivity() {

    private lateinit var dateInput: EditText
    private lateinit var therapistInput: EditText
    private lateinit var saveButton: Button
    private lateinit var appointmentsTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointments)

        dateInput = findViewById(R.id.dateInput)
        therapistInput = findViewById(R.id.therapistInput)
        saveButton = findViewById(R.id.saveButton)
        appointmentsTextView = findViewById(R.id.appointmentsTextView)

        val database = AppDatabase.getDatabase(this)

        // Sauvegarder un rendez-vous
        saveButton.setOnClickListener {
            val date = dateInput.text.toString()
            val therapistName = therapistInput.text.toString()

            if (date.isNotBlank() && therapistName.isNotBlank()) {
                lifecycleScope.launch(Dispatchers.IO) {
                    try {
                        database.openHelper.writableDatabase.execSQL(
                            "INSERT INTO appointments (date, therapistName, notes) VALUES (?, ?, ?)",
                            arrayOf(date, therapistName, "No notes")
                        )
                        runOnUiThread {
                            Toast.makeText(this@AppointmentsActivity, "Rendez-vous enregistré", Toast.LENGTH_SHORT).show()
                        }
                        loadAppointments(database)
                    } catch (e: Exception) {
                        runOnUiThread {
                            Toast.makeText(this@AppointmentsActivity, "Erreur lors de l'enregistrement", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            }
        }

        // Charger les rendez-vous
        loadAppointments(database)
    }

    private fun loadAppointments(database: AppDatabase) {
        lifecycleScope.launch(Dispatchers.IO) {
            val results = StringBuilder()
            try {
                val appointments = database.openHelper.writableDatabase
                    .compileStatement("SELECT date, therapistName, notes FROM appointments")
                results.append("Date: ${appointments.simpleQueryForString()}\n")
                results.append("Therapist: ${appointments.simpleQueryForString()}\n")
            } catch (e: Exception) {
                results.append("Erreur lors de la récupération des rendez-vous.")
            }

            runOnUiThread {
                appointmentsTextView.text = results.toString()
            }
        }
    }
}
