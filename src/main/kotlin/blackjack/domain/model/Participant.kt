package blackjack.domain.model

abstract class Participant(val name: String) {
    protected val hand = Hand()

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

    abstract fun canHit(): Boolean

    abstract fun showHand(): List<Card>

    companion object {
        const val INITIAL_DRAW_COUNT = 2
        const val DEFAULT_DRAW_COUNT = 1

        private const val ERROR_MESSAGE_BLANK_PARTICIPANT_NAME = "참가자의 이름은 공백일 수 없습니다."
    }
}
