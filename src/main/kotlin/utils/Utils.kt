package utils

data class Grid<T>(val rows: Int, val cols: Int, val grid: List<List<T>>) {
    operator fun get(row: Int, col: Int): T? =
        if (row in grid.indices && col in grid[row].indices) grid[row][col] else null

    operator fun get(pos: Pair<Int, Int>) = get(pos.first, pos.second)

    fun toPositions(): List<Pair<Int, Int>> = grid
        .flatMapIndexed { row: Int, _: List<T> -> grid[row].mapIndexed { col: Int, _: T -> row to col } }

    fun adjacent8(row: Int, col: Int): List<Pair<Int, Int>> =
        (-1..1).flatMap { r -> (-1..1).map { c -> row + r to col + c } }
            .filter { it != row to col }

    fun adjacent8(pos: Pair<Int, Int>) = adjacent8(pos.first, pos.second)
}

fun List<String>.toGrid(): Grid<Char> {
    return Grid(
        rows = size,
        cols = get(0).length,
        grid = this.map { it.toCharArray().toList() }
    )
}
