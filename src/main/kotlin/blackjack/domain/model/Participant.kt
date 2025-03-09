package blackjack.domain.model

abstract class Participant(val name: String) {
    private val hand = Hand()

    constructor(name: String, cards: List<Card>) : this(name) {
        accept(cards)
    }

    init {
        require(name.isNotBlank()) { ERROR_MESSAGE_BLANK_PARTICIPANT_NAME }
    }

    fun showHand(count: Int = hand.cards.size): List<Card> {
        return hand.cards.take(count).map { it.copy() }
    }

    fun accept(cards: List<Card>) {
        hand.add(cards)
    }

    fun computeScore(): Int {
        return hand.computeScore()
    }

    fun isBusted(): Boolean {
        return hand.isBusted()
    }

    companion object {
        private const val ERROR_MESSAGE_BLANK_PARTICIPANT_NAME = "참가자의 이름은 공백일 수 없습니다."
    }
}
