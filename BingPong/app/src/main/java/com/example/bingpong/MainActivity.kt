package com.example.pingpongscorekeeper

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bingpong.R
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE_SPEECH_INPUT = 100

    private var player1Score = 0
    private var player2Score = 0
    private var player1Name = "Player 1"
    private var player2Name = "Player 2"

    private lateinit var player1ScoreTextView: TextView
    private lateinit var player2ScoreTextView: TextView
    private lateinit var winnerDisplayTextView: TextView
    private lateinit var player1NameInput: EditText
    private lateinit var player2NameInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Views
        player1ScoreTextView = findViewById(R.id.player1_score)
        player2ScoreTextView = findViewById(R.id.player2_score)
        winnerDisplayTextView = findViewById(R.id.winner_display)
        player1NameInput = findViewById(R.id.player1_name_input)
        player2NameInput = findViewById(R.id.player2_name_input)

        val player1Button: Button = findViewById(R.id.button_player1)
        val player2Button: Button = findViewById(R.id.button_player2)
        val resetButton: Button = findViewById(R.id.button_reset)
        val voiceButton: Button = findViewById(R.id.button_voice)

        player1Button.setOnClickListener {
            player1Name = player1NameInput.text.toString().ifEmpty { "Player 1" }
            player1Score++
            updateScores()
        }

        player2Button.setOnClickListener {
            player2Name = player2NameInput.text.toString().ifEmpty { "Player 2" }
            player2Score++
            updateScores()
        }

        resetButton.setOnClickListener {
            resetGame()
        }

        voiceButton.setOnClickListener {
            startVoiceRecognition()
        }
    }

    private fun startVoiceRecognition() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say a command like 'Add point to Player 1'")

        try {
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Speech recognition is not supported on your device.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SPEECH_INPUT && resultCode == RESULT_OK && data != null) {
            val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            if (!result.isNullOrEmpty()) {
                processVoiceCommand(result[0])
            }
        }
    }

    private fun processVoiceCommand(command: String) {
        when {
            command.contains("add point to player 1", ignoreCase = true) -> {
                player1Name = player1NameInput.text.toString().ifEmpty { "Player 1" }
                player1Score++
                updateScores()
            }
            command.contains("add point to player 2", ignoreCase = true) -> {
                player2Name = player2NameInput.text.toString().ifEmpty { "Player 2" }
                player2Score++
                updateScores()
            }
            else -> {
                Toast.makeText(this, "Command not recognized. Please try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateScores() {
        player1ScoreTextView.text = "$player1Name: $player1Score"
        player2ScoreTextView.text = "$player2Name: $player2Score"
        checkWinner()
    }

    private fun checkWinner() {
        when {
            player1Score >= 11 && player1Score - player2Score >= 2 -> {
                winnerDisplayTextView.text = "Winner: $player1Name"
                disableButtons()
            }
            player2Score >= 11 && player2Score - player1Score >= 2 -> {
                winnerDisplayTextView.text = "Winner: $player2Name"
                disableButtons()
            }
            else -> {
                winnerDisplayTextView.text = "Winner: "
            }
        }
    }

    private fun disableButtons() {
        findViewById<Button>(R.id.button_player1).isEnabled = false
        findViewById<Button>(R.id.button_player2).isEnabled = false
    }

    private fun resetGame() {
        player1Score = 0
        player2Score = 0
        updateScores()
        winnerDisplayTextView.text = "Winner: "
        findViewById<Button>(R.id.button_player1).isEnabled = true
        findViewById<Button>(R.id.button_player2).isEnabled = true
        player1NameInput.text.clear()
        player2NameInput.text.clear()
    }
}
