package blackjack.domain

@JvmInline
value class Betting(
    val amount: Int,
) {
    init {
        require(amount > 0) { "Amount must be greater than 0" }
    }

    fun toProfit(
        state: ParticipantState,
        score: Score,
    ): Int =
        when (state) {
            ParticipantState.WIN -> {
                when (score) {
                    Score.Blackjack -> (amount * 1.5).toInt()
                    else -> amount
                }
            }

            ParticipantState.DRAW -> 0
            ParticipantState.LOSE -> -amount
            ParticipantState.PLAYING -> throw IllegalStateException("Player's result is not decided yet")
        }
}
