package day02

import kotlin.text.substring
import kotlin.text.take

class Day(val input: List<String>) {
    private fun parseInput() = input[0].split(",")
        .map { it.split('-') }
        .map { it[0].toLong() to it[1].toLong() }

    fun starOne() = parseInput()
        .fold(0L) { acc, (l, r) ->
            (l..r).fold(acc) { acc2, num ->
                num.toString().run {
                    when {
                        length.mod(2) == 0 && take(length / 2) == substring(length / 2, length) -> acc2 + num
                        else -> acc2
                    }
                }
            }
        }.run(::println)

    fun starTwo() = parseInput()
        .fold(0L) { acc, (l, r) ->
            (l..r).fold(acc) { acc2, num ->
                num.toString().run {
                    for (i in (length / 2) downTo 1) {
                        if (length.mod(i) != 0) continue
                        if (chunked(i).toSet().size == 1) return@fold acc2 + num
                    }
                    acc2
                }
            }
        }.run(::println)
}
