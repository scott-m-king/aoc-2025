package day03

class Day(val input: List<String>) {
    fun starOne(): Int = input.map { it.map { c -> "$c".toInt() } }.sumOf { line ->
        line.take(line.size - 1).foldIndexed(0 to line[0]) { idx, acc, curr ->
            if (curr > acc.second) (idx to curr)
            else acc
        }.let { (index, f) -> "$f${line.subList(index + 1, line.size).max()}".toInt() }
    }

    fun starTwo(): Long = input.map { it.map { c -> "$c".toInt() } }.sumOf {
        helper(it, "", 0, 0)
    }

    tailrec fun helper(line: List<Int>, curr: String, idx: Int, offset: Int): Long {
        if (idx == 12) return curr.toLong()
        val max = line.slice(offset..(line.size - 12 + idx)).withIndex().maxBy { it.value }
        return helper(line, "$curr${max.value}", idx + 1, offset + max.index + 1)
    }
}
