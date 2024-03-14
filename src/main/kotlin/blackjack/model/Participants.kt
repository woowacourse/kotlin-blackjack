package blackjack.model

class Participants(
    val dealer: Dealer,
    val playerGroup: PlayerGroup,
) {
    fun initSetting(gameDeck: GameDeck) {
        dealer.initHands(gameDeck)
        playerGroup.players.forEach { player ->
            player.initHands(gameDeck)
        }
    }

    fun matchResult() {
        playerGroup.players.forEach { player ->
            player.changeResult((player.state as Finished).decideWinner(dealer))
            dealer.changeResult((dealer.state as Finished).decideWinner(player))
        }
    }
}
