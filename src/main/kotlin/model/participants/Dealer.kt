package model.participants

import model.ParticipantState
import model.card.Deck
import model.result.Point.Companion.compareTo

class Dealer(
    override var participantState: ParticipantState,
    override val participantName: ParticipantName =
        ParticipantName.fromInput(
            DEFAULT_NAME,
        ),
) : Participant(participantState, participantName) {
    fun play(deck: Deck): Int {
        var hitCount = 0

        while (canHit()) {
            hit(deck.pop())
            hitCount++
        }

        return hitCount
    }

    override fun canHit(): Boolean = getPointWithAce() <= HIT_THRESHOLD

    companion object {
        private const val HIT_THRESHOLD = 16
        private const val DEFAULT_NAME = "딜러"
    }
}
