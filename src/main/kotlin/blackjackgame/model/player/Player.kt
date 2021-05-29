package blackjackgame.model.player

import blackjackgame.model.card.Card

class Player(val name: String) {
    val cards = mutableListOf<Card>()

    fun drawCard(card: Card) {
        cards.add(card)
    }

    fun drawCard(cards: List<Card>) {
        this.cards.addAll(cards)
    }

    fun getInitCards(): List<Card> {
        return this.cards.subList(0, 2)
    }

}