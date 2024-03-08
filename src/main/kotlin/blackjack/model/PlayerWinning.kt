package blackjack.model

data class PlayerWinning(val result: Map<PlayerName, WinningResultStatus>) {
    fun judgeDealerWinningResult(): DealerWinning =
        DealerWinning(
            result.values.groupingBy { it.reverse() }
                .fold(initialValue = 0) { accumulator, _ -> accumulator + 1 }.toSortedMap(),
        )
}
