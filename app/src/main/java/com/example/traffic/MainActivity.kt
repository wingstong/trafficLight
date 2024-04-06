package com.example.traffic

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var currentState = 0
    private lateinit var buttonColor: Button
    private val lightColors = arrayOf(Color.RED, Color.YELLOW, Color.GREEN)
    private val handler = Handler(Looper.getMainLooper())
    private val delay = 1000L
    private var isRunning = false


    private val runnable = object : Runnable {
        override fun run() {
            updateLightColors()
            if (isRunning) {
                handler.postDelayed(this, delay)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonColor = findViewById(R.id.button1)
        buttonColor.setOnClickListener {
            if (isRunning) {
                isRunning = false
                buttonColor.text = "Start"
            } else {
                isRunning = true
                buttonColor.text = "Stop"
                handler.post(runnable)
            }
        }

        if (savedInstanceState != null) {
            currentState = savedInstanceState.getInt("currentState", 0)
            isRunning = savedInstanceState.getBoolean("isRunning", false)
            if (isRunning) {
                buttonColor.text = "Stop"
                handler.postDelayed(runnable, delay)
            }
        }
    }

    private fun updateLightColors() {
        val buttons = arrayOf(
            findViewById<Button>(R.id.bRed),
            findViewById<Button>(R.id.bOrange),
            findViewById<Button>(R.id.bGreen)
        )

        buttons.forEach { it.setBackgroundColor(Color.GRAY) }

        val reverse = currentState / 3 % 2 == 1
        val index = if (reverse) 2 - currentState % 3 else currentState % 3
        val colorsOrder = arrayOf(0, 1, 2)
        buttons[colorsOrder[index]].setBackgroundColor(lightColors[colorsOrder[index]])
        currentState++

        if (currentState >= 6) {
            currentState = 0
        }
    }
        //buttons[currentState % 3].setBackgroundColor(lightColors[currentState % 3])
        //currentState++


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentState", currentState)
        outState.putBoolean("isRunning", isRunning)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }
}
