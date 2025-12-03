package day01

import kotlin.text.substring


class Day(val input: List<String>) {
    data class LockState(val pos: Int, val password: Int)

    fun LockState.getNextPos(dir: Char): Int = when (dir) {
        'L' -> (pos - 1) % 100
        'R' -> (pos + 1) % 100
        else -> throw IllegalArgumentException("Invalid dir $dir")
    }

    private fun solve(rotate: (LockState, Int, Char) -> LockState) =
        input.map { Pair(it[0], it.substring(1, it.length).toInt()) }
            .fold(LockState(50, 0)) { currState, (dir, turns) ->
                rotate(currState, turns, dir)
            }.password

    fun starOne() = solve(::rotate1)

    fun starTwo() = solve(::rotate2)

    private tailrec fun rotate1(
        currState: LockState,
        remaining: Int,
        dir: Char
    ): LockState {
        if (remaining == 0) {
            return currState.let {
                if (it.pos == 0) it.copy(password = it.password + 1)
                else it
            }
        }

        return rotate1(currState.copy(pos = currState.getNextPos(dir)), remaining - 1, dir)
    }

    private tailrec fun rotate2(
        currState: LockState,
        remaining: Int,
        dir: Char
    ): LockState {
        if (remaining == 0) return currState

        val nextPos = currState.getNextPos(dir)

        val nextPassword = currState.password.let {
            if (nextPos == 0) it + 1
            else it
        }

        return rotate2(LockState(nextPos, nextPassword), remaining - 1, dir)
    }
}
