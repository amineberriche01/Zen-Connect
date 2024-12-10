package com.example.zenconnect

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.LinearEasing
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ExercisesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercises)

        val breathTextView = findViewById<TextView>(R.id.breathTextView)
        val circleView = findViewById<BreathAnimationView>(R.id.breathAnimationView)

        lifecycleScope.launch {
            while (true) {
                // Inhale
                breathTextView.text = "Inspirez..."
                circleView.animateCircle(1.5f)
                delay(4000)

                // Hold
                breathTextView.text = "Retenez votre souffle..."
                delay(2000)

                // Exhale
                breathTextView.text = "Expirez..."
                circleView.animateCircle(0.5f)
                delay(4000)
            }
        }
    }
}
