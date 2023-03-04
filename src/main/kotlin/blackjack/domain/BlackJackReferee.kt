package blackjack.domain

class BlackJackReferee {

    fun judgePlayerGameResults(players: List<Player>, dealer: Dealer) = players.map { player ->
        PlayerGameResult(
            playerName = player.name.value,
            gameResult = GameResult.valueOf(player.cards.getTotalCardsScore(), dealer.cards.getTotalCardsScore())
        )
    }
}
