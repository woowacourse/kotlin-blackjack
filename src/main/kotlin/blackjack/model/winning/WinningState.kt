package blackjack.model.winning

import blackjack.model.hand.HandState

enum class WinningState(
    val profitRate: Double,
) {
    WIN_BY_BLACKJACK(1.5),
    WIN_DEFAULT(1.0),
    PUSH(0.0),
    LOSE(-1.0),
    ;

    fun reverseToDealer(): WinningState =
        when (this) {
            WIN_BY_BLACKJACK -> LOSE
            WIN_DEFAULT -> LOSE
            LOSE -> WIN_DEFAULT
            PUSH -> PUSH
        }

    companion object {
        fun fromPlayer(
            playerScore: Int,
            dealerScore: Int,
            playerHandState: HandState,
            dealerHandState: HandState,
        ): WinningState =
            when {
                playerHandState == HandState.BLACKJACK && dealerHandState != HandState.BLACKJACK -> WIN_BY_BLACKJACK
                playerHandState == HandState.BLACKJACK && dealerHandState == HandState.BLACKJACK -> PUSH
                dealerHandState == HandState.BLACKJACK -> LOSE

                playerHandState == HandState.BUST -> LOSE
                dealerHandState == HandState.BUST -> WIN_DEFAULT

                playerScore > dealerScore -> WIN_DEFAULT
                playerScore < dealerScore -> LOSE

                else -> PUSH
            }
    }
}
