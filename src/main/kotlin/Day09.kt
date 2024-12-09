import Day09.Entry.File
import Day09.Entry.Space

class Day09(input: List<String>) {

    private val input = input.single().asSequence().map(Char::digitToInt)

    fun part1(): Long = input
        .layout()
        .compact()
        .withIndex()
        .sumOf { (index, value) -> index * (value ?: 0).toLong() }

    private fun Sequence<Int>.layout() = foldIndexedInPlace(mutableListOf<Int?>()) { index, value ->
        val id = if (index % 2 == 0) (index / 2) else null
        repeat(value) { add(id) }
    }

    private class CompactingSearch(val list: MutableList<Int?>, var limit: Int)

    private fun List<Int?>.compact() = asSequence()
        .foldIndexedInPlace(
            initial = CompactingSearch(toMutableList(), limit = lastIndex),
        ) { index, value ->
            if (index >= limit || value != null) return@foldIndexedInPlace
            limit = (limit downTo index).first { list[it] != null }
            list[index] = list[limit]
            list[limit] = null
        }.list

    fun part2(): Long = input
        .layoutEntries()
        .compactWholeFiles()
        .flatten()
        .withIndex()
        .sumOf { (index, value) -> index * (value ?: 0).toLong() }

    private sealed class Entry {
        abstract val size: Int

        data class File(override val size: Int, val id: Int) : Entry()
        data class Space(override val size: Int) : Entry()
    }

    private fun Sequence<Int>.layoutEntries() = foldIndexedInPlace(mutableListOf<Entry>()) { index, value ->
        when {
            value == 0 -> return@foldIndexedInPlace
            index % 2 == 0 -> File(size = value, id = index / 2)
            else -> Space(size = value)
        }.let(::add)
    }

    private fun List<Entry>.compactWholeFiles() = foldRightInPlace(toMutableList()) { entry ->
        if (entry !is File) return@foldRightInPlace
        val entryIndex = indexOf(entry)
        val slot = indexOfFirst { it is Space && it.size >= entry.size }
            .takeIf { it in 0..<entryIndex }
            ?.let { IndexedValue(it, this[it] as Space) }
            ?: return@foldRightInPlace
        this[slot.index] = entry
        this[entryIndex] = Space(entry.size)
        val remainingSpace = slot.value.size - entry.size
        if (remainingSpace > 0) add(slot.index + 1, Space(remainingSpace))
    }

    private fun List<Entry>.flatten() = flatMap { entry ->
        when (entry) {
            is File -> List(entry.size) { entry.id }
            is Space -> List(entry.size) { null }
        }
    }

}

