package day07

import utils.Grid
import utils.toGrid

class Day(val input: List<String>) {
    fun starOne(): Int {
        val grid = input.toGrid()
        val (_, startCol) = grid.positions.find { grid[it] == 'S' } ?: (0 to 0)
        return helper(grid, 1, 0, setOf(startCol))
    }

    tailrec fun helper(grid: Grid<Char>, row: Int, count: Int, beamCols: Set<Int>): Int {
        if (row == grid.cells.size - 1) return count

        val pointCols = grid.positions
            .filter { it.first == row && grid[it] == '^' }
            .map { it.second }
            .toSet()

        val (count, nextBeams) = beamCols.fold(count to setOf<Int>()) { (nextCount, set), curr ->
            if (curr in pointCols) nextCount + 1 to set + setOf(curr - 1, curr + 1)
            else nextCount to set + setOf(curr)
        }

        return helper(grid, row + 1, count, nextBeams)
    }

    fun starTwo() {
        TODO()
    }
}
