package blackjack.domain

import blackjack.Shape

data class Card(val shape: Shape, val cardNumber: CardNumber) {
    fun isAce(): Boolean = cardNumber == CardNumber.ACE
}
