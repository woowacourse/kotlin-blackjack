package blackjack.domain.state

import blackjack.Shape
import blackjack.domain.Card
import blackjack.domain.CardNumber

object Cards {
    val ace = Card(Shape.HEART, CardNumber.ACE)
    val two = Card(Shape.HEART, CardNumber.TWO)
    val three = Card(Shape.HEART, CardNumber.THREE)
    val four = Card(Shape.HEART, CardNumber.FOUR)
    val five = Card(Shape.HEART, CardNumber.FIVE)
    val six = Card(Shape.HEART, CardNumber.SIX)
    val seven = Card(Shape.HEART, CardNumber.SEVEN)
    val eight = Card(Shape.HEART, CardNumber.EIGHT)
    val nine = Card(Shape.HEART, CardNumber.NINE)
    val ten = Card(Shape.HEART, CardNumber.TEN)
    val jack = Card(Shape.HEART, CardNumber.JACK)
    val queen = Card(Shape.HEART, CardNumber.QUEEN)
    val king = Card(Shape.HEART, CardNumber.KING)
}
