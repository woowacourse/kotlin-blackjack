package blackjack.model

import blackjack.model.role.PlayerName

data class Participants(val dealer: Dealer, val players: Players) {
    fun addInitialCards() {
        dealer.addInitialCards()
        players.players.forEach {
            it.addInitialCards()
        }
    }

    fun getDealerSum(): Int = dealer.cardHand.calculateScore()

    fun getPlayerResult(): Map<PlayerName, Int> = players.players.associate { it.name to it.cardHand.calculateScore() }
}
