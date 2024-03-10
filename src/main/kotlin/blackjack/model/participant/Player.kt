package blackjack.model.participant

import blackjack.model.card.Card

class Player(val name: PlayerName) : Role() {
    override fun decideMoreCard() = !isBlackjack() && !isBurst()

    companion object {
        fun createPlayerWithCard(
            name: String,
            card: List<Card>,
        ): Player {
            val player = Player(PlayerName(name))
            card.forEach { card ->
                player.receiveCard(card)
            }
            return player
        }
    }
}
