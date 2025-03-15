package blackjack.domain.model.participant

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Hand

abstract class Participant(val name: String, cards: List<Card>) {
    protected val hand = Hand(cards)

    init {
        require(name.isNotBlank()) { ERROR_MESSAGE_BLANK_PARTICIPANT_NAME }
    }

    fun accept(cards: List<Card>) {
        hand.add(cards)
    }

    fun computePoint(): Int {
        return hand.computePoint()
    }

    fun isBusted(): Boolean {
        return hand.isBusted()
    }

    fun isBlackJack(): Boolean {
        return hand.isBlackJack()
    }

    open fun showHand(): List<Card> {
        return hand.show()
    }

    abstract fun canHit(): Boolean

    companion object {
        const val DEFAULT_DRAW_COUNT = 1

        private const val ERROR_MESSAGE_BLANK_PARTICIPANT_NAME = "참가자의 이름은 공백일 수 없습니다."
    }
}
