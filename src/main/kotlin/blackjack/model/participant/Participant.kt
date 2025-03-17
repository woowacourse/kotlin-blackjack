package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.CardCount
import blackjack.model.hand.Hand
import blackjack.model.hand.HandState
import blackjack.model.hand.Score

abstract class Participant(
    val name: Name,
    private var _money: Money,
    private val hand: Hand,
) {
    abstract val isDrawable: Boolean
    val money: Money get() = _money
    val cards: List<Card> get() = hand.cards

    val handState: HandState get() = hand.state

    val score: Score get() = hand.score()

    abstract fun showInitialCards(): List<Card>

    fun receiveMoney(money: Money) {
        _money += money
    }

    fun payMoney(money: Money) {
        _money -= money
    }

    fun receiveCards(drawCards: (CardCount) -> List<Card>) {
        val count = if (cards.isEmpty()) INITIAL_DRAW_COUNT else DEFAULT_DRAW_COUNT
        hand.addAll(drawCards(count))
    }

    companion object {
        val INITIAL_DRAW_COUNT = CardCount(2)
        val DEFAULT_DRAW_COUNT = CardCount(1)
    }
}
