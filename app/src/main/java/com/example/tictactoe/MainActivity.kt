package com.example.tictactoe

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView

lateinit var gridLayout: GridLayout
lateinit var textView: TextView
var counter: Int = 0 // counter değişkenini global olarak tanımladım

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gridLayout = findViewById(R.id.GridLayout)
        textView = findViewById(R.id.textView)
        val board = Array(3) { IntArray(3) }
        for (i in 0 until gridLayout.childCount) {
            if (gridLayout.getChildAt(i) is ImageView) {
                gridLayout.getChildAt(i).setOnClickListener {
                    val target = gridLayout.indexOfChild(it)
                    val row = target / 3
                    val col = target % 3
                    if (board[row][col] == 0) {
                        if (counter % 2 == 1) {
                            gridLayout.getChildAt(target).setBackgroundColor(Color.RED)
                            board[row][col] = 1
                            counter++
                        } else {
                            gridLayout.getChildAt(target).setBackgroundColor(Color.BLUE)
                            board[row][col] = 2
                            counter++
                        }
                        if (counter >= 5) {
                            val winner = checkWinner(board)
                            if (winner != 0) {
                                textView.text = if (winner == 1) "Kırmızı oyuncu kazandı!" else "Mavi oyuncu kazandı!"
                                gridLayout.isEnabled = false //
                            }
                        }
                    }
                }
            }
        }
    }
    fun onReplayClick(view: View) {
        for (i in 0 until gridLayout.childCount) {
            if (gridLayout.getChildAt(i) is ImageView) {
                gridLayout.getChildAt(i).setBackgroundColor(Color.TRANSPARENT)
            }
        }

        val board = Array(3) { IntArray(3) }


        textView.text = ""

        gridLayout.isEnabled = true
        counter = 0
    }
    private fun checkWinner(board: Array<IntArray>): Int {

        for (i in 0..2) {
            if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                return board[i][0]
            }
        }
        for (i in 0..2) {
            if (board[0][i] != 0 && board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
                return board[0][i]
            }
        }

        if (board[0][0] != 0 && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            return board[0][0]
        }
        if (board[0][2] != 0 && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            return board[0][2]
        }
        return 0
    }
}
