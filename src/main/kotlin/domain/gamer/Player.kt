package domain.gamer

import domain.card.Card

class Player(private val _cards: MutableList<Card> = mutableListOf()) : Participant(_cards) {

    fun checkBurst(): Boolean = _cards.sumOf { it.cardValue.value } > CARD_SUM_MAX_VALUE

    companion object {
        private const val CARD_SUM_MAX_VALUE = 21
    }
}
