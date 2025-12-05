package day04

import utils.toGrid


class Day(val input: List<String>) {
    fun starOne() {
        val grid = input.toGrid()
        val positions = grid.toPositions()
        println(positions)
        TODO()
    }

    fun starTwo() {
        TODO()
    }
}
