package day05

class Day(val input: String) {
    fun starOne(): Int = input.split("\n\n").let { part ->
        val ranges =
            part[0].lines().map { it.split('-').map { num -> num.toLong() }.let { range -> range[0] to range[1] } }
        val ingredients = part[1].lines().map { it.toLong() }
        ingredients.count { ranges.any { (start, end) -> it in start..end } }
    }

    fun starTwo(): Long = input.split("\n\n")[0].lines()
        .map { it.split('-').map { num -> num.toLong() }.let { range -> range[0] to range[1] } }
        .sortedBy { it.first }
        .let {
            val (firstStart, firstEnd) = it[0]
            it.drop(1).fold(firstEnd to firstEnd - firstStart + 1) { acc, (currStart, currEnd) ->
                val (end, result) = acc
                when {
                    currEnd <= end -> acc
                    currStart > end -> currEnd to (result + (currEnd - currStart + 1))
                    else -> currEnd to (result + (currEnd - end))
                }
            }.second
        }
}
