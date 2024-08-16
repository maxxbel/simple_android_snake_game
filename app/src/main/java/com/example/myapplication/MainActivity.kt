package com.example.myapplication

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), OnClickListener {

    private var gameView: GameView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gameView = findViewById<GameView>(R.id.game_view)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LOCKED
    }

    override fun onClick(p0: View?) {
        if (p0!!.id == R.id.btn_left) {
            gameView!!.snake.moveTo(Direction.left)
        }
        if (p0.id == R.id.btn_right) {
            gameView!!.snake.moveTo(Direction.right)
        }
        if (p0.id == R.id.btn_up) {
            gameView!!.snake.moveTo(Direction.up)
        }
        if (p0.id == R.id.btn_down) {
            gameView!!.snake.moveTo(Direction.down)
        }
        if (p0.id == R.id.btn_reset) {
            gameView!!.snake.reset()
        }
    }


}