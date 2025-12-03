package day02

import kotlin.text.substring
import kotlin.text.take

class Day(val input: List<String>) {
    private fun solve(fn: (Long, Long) -> Long) = input[0].split(",")
        .map { it.split('-') }
        .map { it[0].toLong() to it[1].toLong() }
        .fold(0L) { acc, (l, r) -> (l..r).fold(acc) { result, num -> fn(num, result) } }

    fun starOne() = solve { num, acc ->
        num.toString().run {
            when {
                length.mod(2) == 0 && take(length / 2) == substring(length / 2, length) -> acc + num
                else -> acc
            }
        }
    }

    fun starTwo() = solve { num, acc ->
        num.toString().run {
            ((length / 2) downTo 1)
                .any { length.mod(it) == 0 && chunked(it).toSet().size == 1 }
                .let { if (it) acc + num else acc }
        }
    }
}
