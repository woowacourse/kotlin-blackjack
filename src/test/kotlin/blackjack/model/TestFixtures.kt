package blackjack.model

import blackjack.model.entitiy.Card
import blackjack.model.entitiy.CardRank
import blackjack.model.entitiy.Shape

object TestFixtures {
    val HEART_ONE = Card(Shape.HEART, CardRank.ACE)
    val HEART_TWO = Card(Shape.HEART, CardRank.TWO)
    val HEART_THREE = Card(Shape.HEART, CardRank.THREE)
    val HEART_FOUR = Card(Shape.HEART, CardRank.FOUR)
    val HEART_FIVE = Card(Shape.HEART, CardRank.FIVE)
    val HEART_SIX = Card(Shape.HEART, CardRank.SIX)
    val HEART_SEVEN = Card(Shape.HEART, CardRank.SEVEN)
    val HEART_EIGHT = Card(Shape.HEART, CardRank.EIGHT)
    val HEART_NINE = Card(Shape.HEART, CardRank.NINE)
    val HEART_TEN = Card(Shape.HEART, CardRank.TEN)
    val HEART_JACK = Card(Shape.HEART, CardRank.JACK)
    val HEART_QUEEN = Card(Shape.HEART, CardRank.QUEEN)
    val HEART_KING = Card(Shape.HEART, CardRank.KING)
}
