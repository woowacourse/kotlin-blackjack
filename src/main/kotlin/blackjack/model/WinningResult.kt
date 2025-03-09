package blackjack.model

enum class WinningResult {
    WIN,
    PUSH,
    LOSE,
    ;

    companion object {
        fun from(
            dealer: Dealer,
            player: Player,
        ): WinningResult {
            val dealerBlackjack = dealer.hand.cards.size == 2 && dealer.hand.score() == 21
            val playerBlackjack = player.hand.cards.size == 2 && player.hand.score() == 21

            return when {
                dealerBlackjack && playerBlackjack -> PUSH
                playerBlackjack -> WIN
                dealerBlackjack -> LOSE
                dealer.hand.score() < player.hand.score() -> WIN
                dealer.hand.score() > player.hand.score() -> LOSE
                else -> PUSH
            }
        }


    }
}
