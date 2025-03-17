package blackjack

import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.Shape

class CardFixture {
    companion object {
        val CLOVER_ACE = Card(Shape.CLOVER, CardNumber.ACE)
        val CLOVER_TWO = Card(Shape.CLOVER, CardNumber.TWO)
        val CLOVER_THREE = Card(Shape.CLOVER, CardNumber.THREE)
        val CLOVER_FOUR = Card(Shape.CLOVER, CardNumber.FOUR)
        val CLOVER_FIVE = Card(Shape.CLOVER, CardNumber.FIVE)
        val CLOVER_SIX = Card(Shape.CLOVER, CardNumber.SIX)
        val CLOVER_SEVEN = Card(Shape.CLOVER, CardNumber.SEVEN)
        val CLOVER_EIGHT = Card(Shape.CLOVER, CardNumber.EIGHT)
        val CLOVER_NINE = Card(Shape.CLOVER, CardNumber.NINE)
        val CLOVER_TEN = Card(Shape.CLOVER, CardNumber.TEN)
        val CLOVER_JACK = Card(Shape.CLOVER, CardNumber.JACK)
        val CLOVER_QUEEN = Card(Shape.CLOVER, CardNumber.QUEEN)
        val CLOVER_KING = Card(Shape.CLOVER, CardNumber.KING)
        val HEART_ACE = Card(Shape.HEART, CardNumber.ACE)
        val HEART_TWO = Card(Shape.HEART, CardNumber.TWO)
        val HEART_THREE = Card(Shape.HEART, CardNumber.THREE)
        val HEART_FOUR = Card(Shape.HEART, CardNumber.FOUR)
        val HEART_FIVE = Card(Shape.HEART, CardNumber.FIVE)
        val HEART_SIX = Card(Shape.HEART, CardNumber.SIX)
        val HEART_SEVEN = Card(Shape.HEART, CardNumber.SEVEN)
        val HEART_EIGHT = Card(Shape.HEART, CardNumber.EIGHT)
        val HEART_NINE = Card(Shape.HEART, CardNumber.NINE)
        val HEART_TEN = Card(Shape.HEART, CardNumber.TEN)
        val HEART_JACK = Card(Shape.HEART, CardNumber.JACK)
        val HEART_QUEEN = Card(Shape.HEART, CardNumber.QUEEN)
        val HEART_KING = Card(Shape.HEART, CardNumber.KING)
        val SPADE_ACE = Card(Shape.SPADE, CardNumber.ACE)
        val DIAMOND_ACE = Card(Shape.DIAMOND, CardNumber.ACE)
    }
}
