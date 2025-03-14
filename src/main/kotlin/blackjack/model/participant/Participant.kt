package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.hand.Hand
import blackjack.model.hand.HandState
import blackjack.model.hand.Score

abstract class Participant(
    val name: Name,
    private var _money: Money,
    private val hand: Hand,
) {
    val money: Money get() = _money
    abstract val isDrawable: Boolean
    val cards: List<Card> get() = hand.cards

    val handState: HandState get() = hand.state

    val score: Score get() = hand.score()

    abstract fun showInitialCards(): List<Card>

    fun recieveMoney(money: Money) {
        this._money += money
    }

    fun payMoney(money: Money): Money {
        this._money -= money
        return money
    }

    fun recieveCards(recieveCards: (Int) -> List<Card>) {
        val count = if (cards.isEmpty()) INITIAL_DRAW_COUNT else DEFAULT_DRAW_COUNT
        addAll(recieveCards(count))
    }

    fun addAll(cards: List<Card>) {
        hand.addAll(cards)
    }

    companion object {
        const val INITIAL_DRAW_COUNT = 2
        const val DEFAULT_DRAW_COUNT = 1
    }
}
