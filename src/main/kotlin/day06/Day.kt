package day06

import kotlin.text.isWhitespace

class Day(val input: List<String>) {
    data class Pod(val lst: List<List<List<Char>>>, val tmp: List<List<Char>>)
    
    fun getOperator(op: Char): (Long, Long) -> Long = when (op) {
        '*' -> { a, b -> a * b }
        else -> { a, b -> a + b }
    }

    fun starOne(): Long {
        val start = input.map { it.split(" ").filter { text -> text.isNotEmpty() } }
        val nums = start.take(start.size - 1).map { nums -> nums.map { it.toLong() } }
        val ops = start.last().map { getOperator(it[0]) }
        return (0..<nums[0].size).fold(0L) { acc, i ->
            val transposed = nums.map { num -> num[i] }
            acc + transposed.drop(1).fold(transposed[0]) { acc2, curr -> ops[i](acc2, curr) }
        }
    }

    fun starTwo(): Long = input.map { it.toCharArray().toList() }.let { grid ->
        (0..<grid.maxBy { it.size }.size)
            .map { i -> grid.map { it[i] } }
            .fold(Pod(listOf(), listOf())) { (result, tmp), curr ->
                when {
                    curr.all { it.isWhitespace() } -> Pod(result + listOf(tmp), listOf())
                    else -> Pod(result, tmp + listOf(curr.toList()))
                }
            }
            .let { (lst, tmp) -> lst + listOf(tmp) }
            .fold(0L) { result, line ->
                result + line.map {
                    it.dropLast(1).filter { c -> !c.isWhitespace() }.joinToString("").toLong()
                }.let { it.drop(1).fold(it[0]) { acc, curr -> getOperator(line[0].last())(acc, curr) } }
            }
    }
}
