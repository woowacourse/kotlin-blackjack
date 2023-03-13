package domain

import domain.card.Card
import domain.card.CardNumber
import domain.card.CardNumber.ACE
import domain.card.CardNumber.FIVE
import domain.card.CardNumber.FOUR
import domain.card.CardNumber.KING
import domain.card.CardNumber.SIX
import domain.card.CardNumber.TEN
import domain.card.CardNumber.THREE
import domain.card.CardNumber.TWO
import domain.card.CardNumber.values
import domain.card.CardShape.CLOVER
import domain.card.CardShape.HEART
import domain.card.HandOfCards
import domain.person.Dealer
import domain.person.Player

object Dummy {
    val CLOVER_ACE = Card(CLOVER, ACE)
    val HEART_ACE = Card(CLOVER, ACE)
    val CLOVER_TWO = Card(CLOVER, TWO)
    val HEART_TWO = Card(HEART, TWO)
    val CLOVER_THREE = Card(CLOVER, THREE)
    val CLOVER_FOUR = Card(CLOVER, FOUR)
    val CLOVER_FIVE = Card(CLOVER, FIVE)
    val CLOVER_SIX = Card(CLOVER, SIX)
    val CLOVER_TEN = Card(CLOVER, TEN)
    val CLOVER_KING = Card(CLOVER, KING)
    val HEART_KING = Card(HEART, KING)

    fun makeHandOfCards(vararg cardNum: Int): HandOfCards {
        val handOfCards = HandOfCards()
        cardNum.forEach {
            val cardNum = getCardNumber(it)
            handOfCards.addCard(Card(CLOVER, cardNum))
        }
        return handOfCards
    }

    private fun getCardNumber(num: Int): CardNumber {
        return values().find { it.value == num } ?: throw NoSuchElementException("없는 수를 입력하였습니다.")
    }

    fun makeDealer(vararg cards: Card): Dealer {
        val dealer = Dealer()
        cards.forEach {
            dealer.toNextState(it)
        }
        return dealer
    }

    fun makePlayer(name: String, vararg cards: Card): Player {
        val player = Player(name)
        cards.forEach {
            player.toNextState(it)
        }
        return player
    }
}
