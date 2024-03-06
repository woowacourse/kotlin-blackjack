package blackjack.model

class BlackJackGame(
    val dealer: Dealer,
    val participants: Participants,
) {
    fun drawTwoCards(gameDeck: GameDeck) {
        repeat(2) {
            dealer.takeCard(gameDeck.drawCard())
            participants.players.forEach { player ->
                player.takeCard(gameDeck.drawCard())
            }
        }
    }
}
