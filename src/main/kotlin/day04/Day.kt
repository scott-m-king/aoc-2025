package day04

import utils.Grid
import utils.toGrid

class Day(val input: List<String>) {
    fun starOne(): Int = input.toGrid().getFree().count()

    fun starTwo(): Int = helper(input.toGrid()).count()

    tailrec fun helper(grid: Grid, removed: Set<Pair<Int, Int>> = setOf()): Set<Pair<Int, Int>> {
        val free = grid.getFree(removed)
        if (free.isEmpty()) return removed
        return helper(grid, removed.union(free.toSet()))
    }

    fun Grid.getFree(removed: Set<Pair<Int, Int>> = setOf()) = toPositions()
        .filter { pos ->
            !removed.contains(pos) && get(pos) == '@' && (adjacent8(pos).count { get(it) == '@' && !removed.contains(it) } < 4)
        }
}
