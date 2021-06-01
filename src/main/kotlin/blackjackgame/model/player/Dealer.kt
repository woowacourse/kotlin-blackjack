package blackjackgame.model.player

import blackjackgame.model.card.Card

class Dealer : Player("딜러") {

    override fun isPlayer(): Boolean {
        return false
    }

    override fun getInitCards(): List<Card> {
        return this.cards.subList(0, 1)
    }

    fun isAvailableToDraw(): Boolean {
        return this.cards.calculateScore() <= 16
    }
}