package blackjack.domain.model.participant

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Hand
import blackjack.domain.model.result.GameResult

abstract class Participant(val name: String, cards: List<Card>) {
    private val hand = Hand(cards)

    init {
        require(name.isNotBlank()) { ERROR_MESSAGE_BLANK_PARTICIPANT_NAME }
    }

    fun accept(cards: List<Card>) {
        hand.add(cards)
    }

    fun computePoint(): Int {
        return hand.point()
    }

    fun isBusted(): Boolean {
        return hand.isBusted()
    }

    fun isBlackJack(): Boolean {
        return hand.isBlackJack()
    }

    open fun openHand(): List<Card> {
        return hand.open()
    }

    open fun compareAgainst(other: Participant): GameResult {
        if (isBlackJack() && other.isBlackJack()) return GameResult.PUSH
        if (isBlackJack()) return GameResult.BLACKJACK_WIN

        val point: Int = computePoint()
        val otherPoint: Int = other.computePoint()
        return when {
            point > otherPoint -> GameResult.WIN
            point < otherPoint -> GameResult.LOSE
            else -> GameResult.PUSH
        }
    }

    abstract fun canHit(): Boolean

    companion object {
        const val DEFAULT_DRAW_COUNT = 1

        private const val ERROR_MESSAGE_BLANK_PARTICIPANT_NAME = "참가자의 이름은 공백일 수 없습니다."
    }
}
