package domain.gamer

import domain.card.Card

class Player(private val _card: MutableList<Card> = mutableListOf()) : Participant(_card) {

    fun checkBurst(): Boolean = _card.sumOf { it.cardValue.value } > CARD_SUM_MAX_VALUE

    companion object {
        private const val CARD_SUM_MAX_VALUE = 21
    }
}
