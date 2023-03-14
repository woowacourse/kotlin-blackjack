package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.CardMark
import blackjack.domain.card.CardValue

object Fixtures {
    val CLOVER_ACE = Card(CardMark.CLOVER, CardValue.ACE)
    val CLOVER_TWO = Card(CardMark.CLOVER, CardValue.TWO)
    val CLOVER_THREE = Card(CardMark.CLOVER, CardValue.THREE)
    val CLOVER_FOUR = Card(CardMark.CLOVER, CardValue.FOUR)
    val CLOVER_FIVE = Card(CardMark.CLOVER, CardValue.FIVE)
    val CLOVER_SIX = Card(CardMark.CLOVER, CardValue.SIX)
    val CLOVER_SEVEN = Card(CardMark.CLOVER, CardValue.SEVEN)
    val CLOVER_EIGHT = Card(CardMark.CLOVER, CardValue.EIGHT)
    val CLOVER_NINE = Card(CardMark.CLOVER, CardValue.NINE)
    val CLOVER_TEN = Card(CardMark.CLOVER, CardValue.TEN)
    val CLOVER_JACK = Card(CardMark.CLOVER, CardValue.JACK)
    val CLOVER_QUEEN = Card(CardMark.CLOVER, CardValue.QUEEN)
    val CLOVER_KING = Card(CardMark.CLOVER, CardValue.KING)
}
