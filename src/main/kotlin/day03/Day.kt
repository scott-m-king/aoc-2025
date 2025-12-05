package day03

class Day(val input: List<String>) {
    fun starOne(): Long = solve(2)

    fun starTwo(): Long = solve(12)

    fun solve(size: Int): Long = input.map { it.map { c -> "$c".toInt() } }.sumOf {
        helper(it, "", 0, 0, size)
    }

    tailrec fun helper(line: List<Int>, curr: String, idx: Int, offset: Int, size: Int): Long {
        if (idx == size) return curr.toLong()
        val max = line.slice(offset..(line.size - size + idx)).withIndex().maxBy { it.value }
        return helper(line, "$curr${max.value}", idx + 1, offset + max.index + 1, size)
    }
}
