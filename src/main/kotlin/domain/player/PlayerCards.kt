package domain.player

import domain.card.Card

data class PlayerCards(val cards: List<Card> = listOf()) {

    val score = Score(cards)

    fun add(card: Card): PlayerCards {
        return PlayerCards(cards.plus(card))
    }
}