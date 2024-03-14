package blackjack.model

class Participants(
    val dealer: Dealer,
    val players: PlayerGroup,
) {
    fun initSetting(gameDeck: GameDeck) {
        dealer.initHands(gameDeck)
        players.players.forEach { player ->
            player.initHands(gameDeck)
        }
    }

    fun matchResult() {
        players.players.forEach { player ->
            player.changeResult((player.state as Finished).decideWinner(dealer))
            dealer.changeResult((dealer.state as Finished).decideWinner(player))
        }
    }
}
