package model.participants

import model.card.Deck
import model.result.Point.Companion.compareTo
import model.result.ResultType

class Dealer(
    participantState: ParticipantState,
    participantName: ParticipantName =
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

    fun canHit(): Boolean = participantState.hand.calculateOptimalPoint() <= HIT_THRESHOLD

    override fun judge(other: Participant): ResultType {
        return when {
            other.participantState is ParticipantState.Bust -> ResultType.WIN
            participantState is ParticipantState.Bust -> ResultType.LOSE
            participantState.hand.calculateOptimalPoint() > other.participantState.hand.calculateOptimalPoint() -> ResultType.WIN
            participantState.hand.calculateOptimalPoint() == other.participantState.hand.calculateOptimalPoint() -> ResultType.DRAW
            else -> ResultType.LOSE
        }
    }

    companion object {
        private const val HIT_THRESHOLD = 16
        private const val DEFAULT_NAME = "딜러"
    }
}
