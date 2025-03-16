package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.CardCount
import blackjack.model.hand.Hand
import blackjack.model.hand.HandState
import blackjack.model.hand.Score
import blackjack.model.winning.WinningState

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

    fun receiveMoney(money: Money) {
        _money += money
    }

    fun payMoney(money: Money) {
        _money -= money
    }

    fun receiveCards(drawCards: (CardCount) -> List<Card>) {
        val count = if (cards.isEmpty()) INITIAL_DRAW_COUNT else DEFAULT_DRAW_COUNT
        addAll(drawCards(count))
    }

    fun addAll(cards: List<Card>) {
        hand.addAll(cards)
    }

    fun winningState(other: Participant): WinningState =
        when {
            handState == HandState.BLACKJACK && other.handState != HandState.BLACKJACK -> WinningState.WIN_BY_BLACKJACK
            handState == HandState.BLACKJACK && other.handState == HandState.BLACKJACK -> WinningState.PUSH
            other.handState == HandState.BLACKJACK -> WinningState.LOSE

            handState == HandState.BUST -> WinningState.LOSE
            other.handState == HandState.BUST -> WinningState.WIN_DEFAULT

            score > other.score -> WinningState.WIN_DEFAULT
            score < other.score -> WinningState.LOSE

            else -> WinningState.PUSH
        }

    companion object {
        val INITIAL_DRAW_COUNT = CardCount(2)
        val DEFAULT_DRAW_COUNT = CardCount(1)
    }
}
