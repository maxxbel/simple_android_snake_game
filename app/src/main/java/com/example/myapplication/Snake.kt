package com.example.myapplication

import android.graphics.Canvas
import android.graphics.Paint

class Snake {

    val columns = 8
    val rows = 16

    var isDead = false
    var length = 1

    // coordinates of snake head
    var x = 1
    var y = 1

    // size of grid squares
    val size = 100f

    var grid = Array(columns) {IntArray(rows)}

    var direct: Direction = Direction.stop
    var directionChanged = false

    init {
        grid[0][0] = 1
        createApple()
    }

    // resets the game
    fun reset() {
        length = 1
        x = 1
        y = 1
        direct = Direction.stop
        grid = Array(columns) {IntArray(rows)}
        grid[0][0] = 1
        createApple()

        isDead = false
        directionChanged = false
    }

    // draws the next state
    fun draw(canvas: Canvas) {
        for (i in 1..columns) {
            for (j in 1..rows) {
                if (grid[i - 1][j - 1] > 0) {
                    canvas.drawRect(i * size, j * size, i * size + size,
                        j * size + size, Paint())
                }
                if (grid[i - 1][j - 1] == -1) {
                    canvas.drawCircle(i * size + size / 2, j * size + size / 2,
                        size / 2, Paint())
                }
            }

        }
    }

    // creates an apple in empty field
    private fun createApple() {
        var appleCreated = false
        while(!appleCreated) {
            val first = (0..(columns - 1)).random()
            val second = (0..(rows - 1)).random()
            if (grid[first][second] == 0) {
                appleCreated = true
                grid[first][second] = -1
            }
        }
    }

    // changes direction of the snake
    fun moveTo(d : Direction) {
        if (direct == Direction.up && d == Direction.down) {
            return
        }
        if (direct == Direction.down && d == Direction.up) {
            return
        }
        if (direct == Direction.right && d == Direction.left) {
            return
        }
        if (direct == Direction.left && d == Direction.right) {
            return
        }
        if (directionChanged) {
            return
        }
        direct = d
        directionChanged = true
    }

    // called when snake moves towards direction
    // contains game logic
    fun move() {

        when (direct) {
            Direction.stop -> return
            Direction.left -> {
                if (x == 1) {
                    isDead = true
                    return
                }
                --x
            }
            Direction.right -> {
                if (x == columns) {
                    isDead = true
                    return
                }
                ++x
            }
            Direction.up -> {
                if (y == 1) {
                    isDead = true
                    return
                }
                --y
            }
            Direction.down -> {
                if (y == rows) {
                    isDead = true
                    return
                }
                ++y
            }
        }

        // move the snake to new head position
        if (grid[x - 1][y - 1]  == 0) {
            for (i in 1..8) {
                for (j in 1..16) {
                    if (grid[i - 1][j - 1] > 0) {
                        --grid[i - 1][j - 1]
                    }
                }
            }
            grid[x - 1][y - 1] = length
        } else if (grid[x - 1][y - 1] == -1) {
            ++length
            grid[x - 1][y - 1] = length
            createApple()
        } else {
            isDead = true
        }

        directionChanged = false
    }

}