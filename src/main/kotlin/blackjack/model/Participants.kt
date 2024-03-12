package blackjack.model

data class Participants(val dealer: Dealer, val players: Players) {
    fun addInitialCards() {
        dealer.addInitialCards()
        players.players.forEach {
            it.addInitialCards()
        }
    }

    fun getPlayerResult(): Map<PlayerName, Int> = players.players.associate { it.name to it.cardHand.sum() }
}
