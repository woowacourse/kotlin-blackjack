package blackjack.domain.carddeck.cardnumbergenerator

import blackjack.Shape
import blackjack.domain.CardNumber

interface CardNumberGenerator {
    fun getCardNumber(shape: Shape): CardNumber?
}
