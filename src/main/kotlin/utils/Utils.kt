package utils

data class Grid<T>(val rows: Int, val cols: Int, val cells: List<List<T>>) {
    val positions: List<Pair<Int, Int>> = cells
        .flatMapIndexed { row: Int, _: List<T> -> cells[row].mapIndexed { col: Int, _: T -> row to col } }

    operator fun get(row: Int, col: Int): T? =
        if (isValidRow(row) && isValidCol(col)) cells[row][col] else null

    operator fun get(pos: Pair<Int, Int>) = get(pos.first, pos.second)

    fun isEmpty(): Boolean = cells.isEmpty()
    
    fun isValidRow(row: Int): Boolean = row in cells.indices

    fun isValidCol(col: Int): Boolean = col in cells[0].indices

    fun adjacent8(row: Int, col: Int): List<Pair<Int, Int>> =
        (-1..1).flatMap { r -> (-1..1).map { c -> row + r to col + c } }
            .filter { it != row to col }

    fun adjacent8(pos: Pair<Int, Int>) = adjacent8(pos.first, pos.second)
}

fun List<String>.toGrid(): Grid<Char> {
    return Grid(
        rows = size,
        cols = get(0).length,
        cells = this.map { it.toCharArray().toList() }
    )
}
