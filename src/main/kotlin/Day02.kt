class Day02(input: List<String>) {

    private val reports = input.map { it.split(" ").map(String::toInt) }

    fun part1() = reports
        .count { it.isSafelyIncreasing() || it.isSafelyDecreasing() }

    private fun List<Int>.diffs() = windowed(2) { (a, b) -> b - a }
    private fun List<Int>.isSafelyIncreasing() = diffs().all { it in 1..3 }
    private fun List<Int>.isSafelyDecreasing() = diffs().all { it in -3..-1 }

    fun part2() = reports
        .count { it.isSafelyIncreasingWithDampener() || it.isSafelyDecreasingWithDampener() }

    private fun List<Int>.isSafelyIncreasingWithDampener() = isSafelyIncreasing() || indices.any { idx -> minusIndex(idx).isSafelyIncreasing() }
    private fun List<Int>.isSafelyDecreasingWithDampener() = isSafelyDecreasing() || indices.any { idx -> minusIndex(idx).isSafelyDecreasing() }
    private fun List<Int>.minusIndex(index: Int) = toMutableList().apply { removeAt(index) }

}
