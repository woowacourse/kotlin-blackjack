package blackjack.domain

object BlackJackReferee {

    fun judgeGameResult(players: List<Player>, dealer: Dealer) = players.map { player ->
        PlayerGameResult(
            playerName = player.name.value,
            gameResult = GameResult.valueOf(player.cardHand.getTotalCardsScore(), dealer.cardHand.getTotalCardsScore())
        )
    }
}
