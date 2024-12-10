package com.example.zenconnect

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ArticleDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        val title = intent.getStringExtra("title") ?: "Titre inconnu"
        val description = intent.getStringExtra("description") ?: "Description non disponible"
        val imageResId = intent.getIntExtra("image", R.drawable.default_image)

        val titleTextView = findViewById<TextView>(R.id.detailTitle)
        val descriptionTextView = findViewById<TextView>(R.id.detailDescription)
        val imageView = findViewById<ImageView>(R.id.detailImage)

        titleTextView.text = title
        descriptionTextView.text = description
        imageView.setImageResource(imageResId)
    }
}
