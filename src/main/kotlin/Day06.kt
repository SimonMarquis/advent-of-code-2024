import Day06.Direction.UP

class Day06(input: List<String>) {

    private val world = input.toWorld().also { println(it); println() }

    private fun List<String>.toWorld() = World(map { it.map(Char::toChar).toTypedArray() }.toTypedArray())

    data class Coordinate(val x: Int, val y: Int)

    enum class Direction(val dx: Int, val dy: Int) { UP(0, -1), RIGHT(1, 0), DOWN(0, 1), LEFT(-1, 0) }

    class World(private val map: Array<Array<Char>>) {
        private val obstructions: List<Coordinate> = walk().filter { (c, _) -> c == '#' }.map { (_, coords) -> coords }.toList()
        private val start: Coordinate = walk().single { (c, _) -> c == '^' }.second

        private fun walk() = sequence { map.asSequence().forEachIndexed { y, line -> line.forEachIndexed { x, c -> yield(c to Coordinate(x, y)) } } }

        fun patrolling(print: Boolean = false): LinkedHashSet<Coordinate> = LinkedHashSet<Coordinate>().apply {
            var (guard, direction) = (start to UP)
            add(guard)
            while (true) {
                when (val next = guard + direction) {
                    !in map -> return@apply
                    in obstructions -> direction = direction.next()
                    else -> guard = next.also(::add)
                }
                if (print) println(toString(visited = this) + '\n')
            }
        }

        fun isPatrollingOnInfiniteLoop(obstruction: Coordinate, bootstrap: MutableList<Pair<Coordinate, Direction>>, index: Int): Boolean {
            if (obstruction == start) return false.also { bootstrap.add(start to UP) }
            var (guard, direction) = bootstrap.last()
            val newVisited = bootstrap.toMutableSet()
            while (true) {
                when (val next = guard + direction) {
                    !in map -> return false
                    in obstructions, obstruction -> direction = direction.next()
                    else -> guard = next
                }
                if (guard to direction in newVisited) return true
                if (bootstrap.size < index) bootstrap.add(guard to direction)
                newVisited += guard to direction
            }
        }

        operator fun Coordinate.plus(d: Direction) = copy(x = x + d.dx, y = y + d.dy)
        operator fun Array<Array<Char>>.contains(c: Coordinate) = c.x in this[0].indices && c.y in indices

        override fun toString(): String = toString(emptySet())
        fun toString(visited: Set<Coordinate>): String = map.withIndex().joinToString("\n") { (y, chars) ->
            chars.withIndex().joinToString("") { (x, c) ->
                when (Coordinate(x, y)) {
                    in obstructions -> "#"
                    in visited -> "X"
                    else -> c.toString()
                }
            }
        }
    }

    fun part1() = world.patrolling()
        .also { println(world.toString(visited = it)) }
        .size

    fun part2(): Int = world.patrolling().withIndex().run {
        val bootstrap = mutableListOf<Pair<Coordinate, Direction>>()
        count { (index, obstruction) -> world.isPatrollingOnInfiniteLoop(obstruction, bootstrap, index) }
    }

}
