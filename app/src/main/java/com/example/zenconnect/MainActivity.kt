package com.example.zenconnect

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val CHANNEL_ID = "welcome_channel"
    private val NOTIFICATION_ID = 1
    private val REQUEST_CODE_NOTIFICATION_PERMISSION = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Demander l'autorisation d'envoyer des notifications
        requestNotificationPermission()

        // Créer un canal de notification pour Android 8.0+
        createNotificationChannel()

        // Envoyer une notification de bienvenue
        sendWelcomeNotification()


        // Configuration des boutons de l'écran principal
        findViewById<Button>(R.id.viewAppointmentsButton).setOnClickListener {
            startActivity(Intent(this, AppointmentsActivity::class.java))
        }

        findViewById<Button>(R.id.viewArticlesButton).setOnClickListener {
            startActivity(Intent(this, ArticlesActivity::class.java))
        }

        findViewById<Button>(R.id.viewExercisesButton).setOnClickListener {
            startActivity(Intent(this, ExercisesActivity::class.java))
        }
        // Gestion de la barre de navigation en bas
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_appointments -> {
                    startActivity(Intent(this, AppointmentsActivity::class.java))
                    true
                }
                R.id.navigation_articles -> {
                    startActivity(Intent(this, ArticlesActivity::class.java))
                    true
                }
                R.id.navigation_exercises -> {
                    startActivity(Intent(this, ExercisesActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    /**
     * Demander l'autorisation de notifications si nécessaire.
     */
    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    REQUEST_CODE_NOTIFICATION_PERMISSION
                )
            }
        }
    }

    /**
     * Résultat de la demande d'autorisation.
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_NOTIFICATION_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Autorisation accordée, envoyer la notification
                sendWelcomeNotification()
            } else {
                // Autorisation refusée, afficher un message ou gérer l'erreur
            }
        }
    }

    private fun sendWelcomeNotification() {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher) // Assurez-vous d'avoir une icône valide
            .setContentTitle("Welcome Home")
            .setContentText("Welcome to ZenConnect!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(NOTIFICATION_ID, builder.build())
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Welcome Channel"
            val descriptionText = "Channel for welcome notifications"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}
