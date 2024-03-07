package blackjack.model

data class PlayerWinning(val result: Map<String, WinningResultStatus>) {
    fun judgeDealerWinningResult(): Map<WinningResultStatus, Int> =
        result.values.groupingBy { it.reverse() }
            .fold(initialValue = 0) { accumulator, _ -> accumulator + 1 }.toSortedMap()
}
