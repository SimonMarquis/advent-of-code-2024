import com.github.shiguruikai.combinatoricskt.combinations

class Day08(input: List<String>) {

    data class XY(val x: Int, val y: Int)

    private val Pair<XY, XY>.dx get() = first.x - second.x
    private val Pair<XY, XY>.dy get() = first.y - second.y

    private val map = input.map { it.map(Char::toChar).toTypedArray() }.toTypedArray()

    private fun walk() = sequence { map.asSequence().forEachIndexed { y, line -> line.forEachIndexed { x, c -> yield(c to XY(x, y)) } } }

    private fun antennas() = walk()
        .filter { (c, _) -> c != '.' }
        .groupBy({ (c, _) -> c }, { (_, v) -> v })
        .values

    private fun Collection<List<XY>>.pairs() = flatMap { it.combinations(length = 2) }.map(List<XY>::toPair)

    private operator fun Array<Array<Char>>.contains(xy: XY) = xy.x in map[0].indices && xy.y in map.indices

    fun part1(): Int = antennas().pairs()
        .flatMap { it.antinodes() }
        .filter { it in map }
        .distinct()
        .count()

    private fun Pair<XY, XY>.antinodes(): List<XY> = listOf(
        XY(first.x + dx, first.y + dy),
        XY(second.x - dx, second.y - dy),
    )

    fun part2(): Int = antennas().pairs()
        .flatMap { it.harmonics() }
        .distinct()
        .count()

    private fun Pair<XY, XY>.harmonics(): Set<XY> = buildSet {
        fun build(lambda: (Int) -> XY) = generateSequence(0, Int::inc)
            .map(lambda)
            .takeWhile { it in map }
            .let(::addAll)
        build { XY(first.x + dx * it, first.y + dy * it) }
        build { XY(second.x - dx * it, second.y - dy * it) }
    }

}
