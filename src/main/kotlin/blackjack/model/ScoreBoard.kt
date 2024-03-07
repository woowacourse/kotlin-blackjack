package blackjack.model

data class ScoreBoard(
    val playersResult: List<PlayerResult>,
    val dealerResult: DealerResult,
) {
    companion object {
        fun from(playersResult: List<PlayerResult>): ScoreBoard {
            val dealerResult =
                DealerResult(
                    playersResult
                        .groupingBy { it.state.reversed() }
                        .eachCount(),
                )
            return ScoreBoard(playersResult, dealerResult)
        }
    }
}
