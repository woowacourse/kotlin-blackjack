package domain

import domain.card.Card
import domain.card.CardNumber.ACE
import domain.card.CardNumber.FIVE
import domain.card.CardNumber.FOUR
import domain.card.CardNumber.KING
import domain.card.CardNumber.SIX
import domain.card.CardNumber.TEN
import domain.card.CardNumber.THREE
import domain.card.CardNumber.TWO
import domain.card.CardShape.CLOVER
import domain.card.CardShape.HEART

object Dummy {
    val CLOVER_ACE = Card(CLOVER, ACE)
    val HEART_ACE = Card(CLOVER, ACE)
    val CLOVER_TWO = Card(CLOVER, TWO)
    val HEART_TWO = Card(CLOVER, TWO)
    val CLOVER_THREE = Card(CLOVER, THREE)
    val CLOVER_FOUR = Card(CLOVER, FOUR)
    val CLOVER_FIVE = Card(CLOVER, FIVE)
    val CLOVER_SIX = Card(CLOVER, SIX)
    val CLOVER_TEN = Card(CLOVER, TEN)
    val CLOVER_KING = Card(CLOVER, KING)
    val HEART_KING = Card(HEART, KING)
}
