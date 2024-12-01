import kotlin.math.absoluteValue

class Day01(input: List<String>) {

    private val values: Pair<LongArray, LongArray> = input.asSequence()
        .map { it.split("   ").map(String::toLong).take(2) }
        .foldIndexedInPlace(LongArray(input.size) to LongArray(input.size)) { i, (a, b) -> first[i] = a; second[i] = b }
    private val left = values.first
    private val right = values.second

    fun part1(): Long = (left.sorted() zip right.sorted()).sumOf { (a, b) -> (a - b).absoluteValue }

    fun part2(): Long = with(right.groupBy { it }) {
        left.sumOf { it * getOrDefault(it, emptyList()).size.toLong() }
    }

}
