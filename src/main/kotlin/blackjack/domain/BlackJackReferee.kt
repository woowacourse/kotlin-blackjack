package blackjack.domain

class BlackJackReferee {

    fun judgeGameResult(players: List<Player>, dealer: Dealer) = players.map { player ->
        PlayerGameResult(
            playerName = player.name.value,
            gameResult = GameResult.valueOf(player.cards.getTotalCardsValue(), dealer.cards.getTotalCardsValue())
        )
    }
}
