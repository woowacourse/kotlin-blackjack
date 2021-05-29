package blackjackgame.model

import blackjackgame.model.card.Card

class Player(val name: String) {
    val cards = mutableListOf<Card>()

    fun drawCard(card: Card) {
        cards.add(card)
    }

    fun drawCard(cards: List<Card>) {
        this.cards.addAll(cards)
    }

}