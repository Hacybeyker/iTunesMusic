package com.hacybeyker.entities

fun Int.toTime(): Pair<Int, Int> {
    this.let {
        var time = Pair(0, 0)
        if (it > 0) {
            val milliseconds: Int = this
            val minutes = milliseconds / 1000 / 60
            val seconds = milliseconds / 1000 % 60
            time = Pair(minutes, seconds)
        }
        return time
    }
}