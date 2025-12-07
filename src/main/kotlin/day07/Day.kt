package day07

import utils.Grid
import utils.toGrid

class Day(val input: List<String>) {
    fun starOne(): Int = input.toGrid().let { grid ->
        (grid to grid.positions.find { grid[it] == 'S' })
    }.let { (grid, start) ->
        helper(grid, 1, 0, setOf(start?.second ?: 0))
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

    fun starTwo(): Long {
        val grid = input.toGrid()
        val (_, startCol) = grid.positions.find { grid[it] == 'S' } ?: (0 to 0)
        return helper2(grid, 1, startCol)
    }

    fun helper2(grid: Grid<Char>, row: Int, col: Int, cache: MutableMap<Pair<Int, Int>, Long> = mutableMapOf()): Long {
        if (row == grid.cells.size - 1) {
            return 1
        }

        if ((row to col) in cache) {
            return cache[(row to col)] ?: throw IllegalStateException("bad")
        }
       
        if (grid[row, col] == '^') {
            val left = helper2(grid, row + 1, col + 1, cache)
            val right = helper2(grid, row + 1, col - 1, cache)
            return (left + right).apply { cache[(row to col)] = this }
        }

        return helper2(grid, row + 1, col, cache).apply { cache[(row to col)] = this }
    }
}
