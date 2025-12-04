package day03

class Day(val input: List<String>) {
    fun starOne(): Int = input.map { it.map { c -> "$c".toInt() } }.sumOf { line ->
        line.take(line.size - 1).foldIndexed(0 to line[0]) { idx, acc, curr ->
            if (curr > acc.second) (idx to curr)
            else acc
        }.let { (index, f) -> "$f${line.subList(index + 1, line.size).max()}".toInt() }
    }

    fun starTwo() {
        val lines = input.map { it.mapIndexed { i, c -> "$c".toInt() } }

        for (line in lines) {
            TODO()
        }
    }
}
