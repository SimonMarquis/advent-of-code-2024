class Day03(input: List<String>) {

    private val input = input.joinToString(separator = "")

    fun part1(): Int = input.compute()

    private val op = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()

    fun part2(): Int = input
        .replace("""don't\(\).*?(do\(\)|$)""".toRegex(), "")
        .compute()

    private fun String.compute() = op.findAll(this)
        .map(MatchResult::destructured)
        .sumOf { (a, b) -> a.toInt() * b.toInt() }

}
