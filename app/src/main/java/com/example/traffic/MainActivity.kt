package com.example.traffic
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private var currentColor = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var lights = arrayOf(
            findViewById<Button>(R.id.bRed),
            findViewById<Button>(R.id.bOrange),
            findViewById<Button>(R.id.bGreen)
        )
    }

    fun start(view: View) {
        while (true)
        {

        }
    }
}