package day02

import kotlin.text.substring
import kotlin.text.take

class Day(val input: List<String>) {
    fun starOne() = input[0].split(",")
        .map { it.split('-') }
        .map { it[0].toLong() to it[1].toLong() }
        .fold(0L) { acc, (l, r) ->
            (l..r).fold(acc) { acc2, num ->
                num.toString().run {
                    when {
                        length % 2 == 0 && take(length / 2) == substring(length / 2, length) -> acc2 + num
                        else -> acc2
                    }
                }
            }
        }.run(::println)

    fun starTwo() {
        TODO()
    }
}
