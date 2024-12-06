fun <T> Sequence<T>.infinite() = sequence { while (true) yieldAll(this@infinite) }

inline fun <T, R> Sequence<T>.foldInPlace(
    initial: R,
    operation: R.(T) -> Unit,
): R = fold(initial) { acc: R, t: T -> acc.apply { operation(t) } }

inline fun <T, R> Sequence<T>.foldIndexedInPlace(
    initial: R,
    operation: R.(index: Int, T) -> Unit,
): R = foldIndexed(initial) { index: Int, acc: R, t: T -> acc.apply { operation(index, t) } }

fun <E> List<E>.splitParts(
    delimiter: E,
): List<List<E>> = asSequence().foldInPlace<E, MutableList<MutableList<E>>>(mutableListOf(mutableListOf())) {
    if (it == delimiter) add(mutableListOf()) else last().add(it)
}

inline fun <reified T : Enum<T>> T.next(): T = enumValues<T>().run { this[(ordinal + 1) % size] }
