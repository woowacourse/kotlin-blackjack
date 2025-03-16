package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.CardCount
import blackjack.model.hand.Hand
import blackjack.model.hand.HandState
import blackjack.model.hand.HandState.BLACKJACK
import blackjack.model.hand.HandState.BUST
import blackjack.model.hand.Score
import blackjack.model.winning.WinningState
import blackjack.model.winning.WinningState.LOSE
import blackjack.model.winning.WinningState.PUSH
import blackjack.model.winning.WinningState.WIN_BY_BLACKJACK
import blackjack.model.winning.WinningState.WIN_DEFAULT

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
        hand.addAll(drawCards(count))
    }

    fun winningState(other: Participant): WinningState =
        when {
            handState == BLACKJACK && other.handState != BLACKJACK -> WIN_BY_BLACKJACK
            handState == BLACKJACK && other.handState == BLACKJACK -> PUSH
            other.handState == BLACKJACK -> LOSE

            handState == BUST -> LOSE
            other.handState == BUST -> WIN_DEFAULT

            score > other.score -> WIN_DEFAULT
            score < other.score -> LOSE

            else -> PUSH
        }

    companion object {
        val INITIAL_DRAW_COUNT = CardCount(2)
        val DEFAULT_DRAW_COUNT = CardCount(1)
    }
}
