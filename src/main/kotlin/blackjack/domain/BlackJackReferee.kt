package blackjack.domain

object BlackJackReferee {
    const val BLACK_JACK_SCORE = 21

    fun judgeGameResult(player: Player, dealer: Dealer): GameResult {
        val playerScore = player.cardHand.getTotalCardsScore()
        val dealerScore = dealer.cardHand.getTotalCardsScore()

        return when {
            playerScore > 21 -> GameResult.LOSE
            dealerScore > 21 -> GameResult.WIN
            player.cardHand.isBlackJack() -> GameResult.BLACKJACK
            playerScore > dealerScore -> GameResult.WIN
            playerScore < dealerScore -> GameResult.LOSE
            else -> GameResult.DRAW
        }
    }
}
