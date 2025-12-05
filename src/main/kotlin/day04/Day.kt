package day04

import utils.toGrid


class Day(val input: List<String>) {
    fun starOne(): Int = input.toGrid().let { grid ->
        grid.toPositions()
            .filter { pos -> grid[pos] == '@' }
            .count { pos -> grid.adjacent8(pos).count { it == '@' } < 4 }
    }

    fun starTwo() {
        TODO()
    }
}
