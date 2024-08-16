package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.os.CountDownTimer
import android.util.AttributeSet
import android.view.View

class GameView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var snake: Snake = Snake()

    var timer = object : CountDownTimer(Long.MAX_VALUE, 500) {
        override fun onTick(p0: Long) {
            if (snake.isDead) {
                snake.reset()
                return
            }
            snake.move()
            invalidate()
        }
        override fun onFinish() {
        }
    }

    init {
        timer.start()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        if (snake.isDead) {
            return
        }
        super.onDraw(canvas)
        snake.draw(canvas)
    }

}