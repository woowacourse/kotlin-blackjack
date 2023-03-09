package blackjack.domain

object BlackJackReferee {
    const val BLACK_JACK_SCORE = 21

    fun judgeGameResult(players: List<Player>, dealer: Dealer) = players.map { player ->
        PlayerGameResult(
            playerName = player.name.value,
            gameResult = GameResult.valueOf(player.cardHand.getTotalCardsScore(), dealer.cardHand.getTotalCardsScore()),
            isBlackJack = isBlackJack(player.cardHand)
        )
    }

    fun isBlackJack(cardHand: CardHand): Boolean {
        return cardHand.size == CardHand.INITIAL_CARDS_SIZE && cardHand.getTotalCardsScore() == BLACK_JACK_SCORE
    }
}
