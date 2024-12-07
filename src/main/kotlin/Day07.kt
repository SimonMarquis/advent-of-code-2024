import com.github.shiguruikai.combinatoricskt.permutationsWithRepetition

class Day07(input: List<String>) {

    private val equations: List<Equation> = input
        .map { it.split(": ") }
        .map { (a, b) -> a.toLong() to b.split(" ").map(String::toLong) }

    private val plus: Operation = Long::plus
    private val times: Operation = Long::times
    private val concat: Operation = { a, b -> (a.toString() + b.toString()).toLong() }

    fun part1() = solve(setOf(plus, times))
    fun part2() = solve(setOf(plus, times, concat))

    private fun solve(operations: Set<Operation>) = equations
        .filter { it(operations) }
        .sumOf { it.value }

    private operator fun Equation.invoke(operations: Set<Operation>): Boolean = operations
        .permutationsWithRepetition(numbers.size - 1)
        .any { value == numbers.reduceIndexed { index, acc, i -> it[index - 1](acc, i) } }

}

typealias Equation = Pair<Long, List<Long>>
typealias Operation = (Long, Long) -> Long

val Equation.value get() = first
val Equation.numbers get() = second
