package blackjack.model

data class PlayerWinning(val result: Map<PlayerName, WinningResultStatus>) {
    fun judgeDealerWinningResult(): DealerWinning =
        DealerWinning(
            result.values.groupingBy { it.reverse() }
                .eachCount(),
        )
}
