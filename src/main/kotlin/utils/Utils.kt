package utils

data class Grid(val rows: Int, val cols: Int, val grid: List<String>) {
    operator fun get(row: Int, col: Int): Char? =
        if (row in grid.indices && col in grid[row].indices) grid[row][col] else null

    operator fun get(pos: Pair<Int, Int>) = get(pos.first, pos.second)

    fun toPositions(): List<Pair<Int, Int>> = grid
        .flatMapIndexed { row, _ -> grid[row].mapIndexed { col, _ -> row to col } }

    fun adjacent8(row: Int, col: Int): List<Char> =
        (-1..1).flatMap { r -> (-1..1).map { c -> row + r to col + c } }
            .filter { it != row to col }
            .mapNotNull { get(it) }

    fun adjacent8(pos: Pair<Int, Int>) = adjacent8(pos.first, pos.second)
}

fun List<String>.toGrid(): Grid {
    return Grid(
        rows = size,
        cols = get(0).length,
        grid = this
    )
}
