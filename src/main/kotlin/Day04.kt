class Day04(input: List<String>) {

    private val world = input.toWorld()

    fun part1(): Int = world.walk()
        .flatMap { it.neighbours() }
        .map { it.asString() }
        .count { it == "XMAS" }

    fun part2(): Int = world.walk()
        .map { it.neighboursInX() }
        .map { neighbours -> neighbours.map { it.asString() } }
        .count { neighbours -> neighbours.all { it == "MAS" || it == "SAM" } }

    private fun List<String>.toWorld(): Array<CharArray> = Array(size) { x -> CharArray(this[x].length) { y -> this[x][y] } }
    private fun Array<CharArray>.walk(): Sequence<Pair<Int, Int>> = indices.asSequence().flatMap { x -> get(x).indices.asSequence().map { y -> x to y } }
    private fun List<Pair<Int, Int>>.asString() = mapNotNull { world[it] }.joinToString("")
    private operator fun Array<CharArray>.get(it: Pair<Int, Int>) = getOrNull(it.first)?.getOrNull(it.second)

    private fun Pair<Int, Int>.neighbours(): List<List<Pair<Int, Int>>> = listOf(
        /*↑*/ List(4) { first /**/ to second - it },
        /*↗*/ List(4) { first + it to second - it },
        /*→*/ List(4) { first + it to second /**/ },
        /*↘*/ List(4) { first + it to second + it },
        /*↓*/ List(4) { first /**/ to second + it },
        /*↙*/ List(4) { first - it to second + it },
        /*←*/ List(4) { first - it to second /**/ },
        /*↖*/ List(4) { first - it to second - it },
    )

    private fun Pair<Int, Int>.neighboursInX(): List<List<Pair<Int, Int>>> = listOf(
        /*↖↘*/ listOf(first - 1 to second - 1, this, first + 1 to second + 1),
        /*↙↗*/ listOf(first - 1 to second + 1, this, first + 1 to second - 1),
    )

}
