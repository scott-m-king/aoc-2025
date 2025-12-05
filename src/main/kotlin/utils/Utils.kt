package utils

data class Grid(val rows: Int, val cols: Int, val grid: List<String>) {
    operator fun get(row: Int, col: Int): Char? =
        if (row in grid.indices && col in grid[row].indices) grid[row][col] else null

    fun toPositions(): List<Pair<Int, Int>> = grid
        .flatMapIndexed { row, _ -> grid[row].mapIndexed { col, _ -> row to col } }
}

fun List<String>.toGrid(): Grid {
    return Grid(
        rows = size,
        cols = get(0).length,
        grid = this
    )
}