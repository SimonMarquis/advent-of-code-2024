class Day05(input: List<String>) {

    private val input = input.splitParts(delimiter = "").let { (rules, updates) ->
        Pair(
            first = rules
                .map { it.split("|").map(String::toInt).take(2) }
                .groupBy { it.first() }
                .mapValues { entry -> entry.value.map { (_, b) -> b }.toSet() }
                .withDefault { emptySet() },
            second = updates
                .map { it.split(",").map(String::toInt) },
        )
    }

    fun part1() = input
        .let { (rules, updates) -> updates.partition(rules) }
        .let { (sorted, _) -> sorted }
        .sumOf { it.middle() }

    private fun List<Update>.partition(rules: RulesMap) = partition { it.isSorted(rules) }
    private fun Update.isSorted(rules: RulesMap) = windowed(2).all { (l, r) -> (r in rules.getValue(l)) }
    private fun Update.middle(): Int = get(size / 2)

    fun part2() = input.let { (rules, updates) -> updates.partition(rules) }
        .let { (_, unsorted) -> unsorted }
        .map { it.sorted(rules = input.first) }
        .sumOf { it.middle() }


    private fun Update.sorted(rules: RulesMap): Update = sortedWith { l, r ->
        when {
            r in rules.getValue(l) -> 0
            l in rules.getValue(r) -> -1
            else -> +1
        }
    }

}

typealias Update = List<Int>
typealias RulesMap = Map<Int, Set<Int>>
