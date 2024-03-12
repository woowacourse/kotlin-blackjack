package blackjack.model

data class Participants(val dealer: Dealer, val players: Players) {
    fun addInitialCards(cardGenerator: CardGenerator) {
        dealer.addInitialCards(cardGenerator)
        players.players.forEach {
            it.addInitialCards(cardGenerator)
        }
    }

    fun getPlayerResult(): Map<PlayerName, Int> = players.players.associate { it.name to it.cardHand.sum() }
}
