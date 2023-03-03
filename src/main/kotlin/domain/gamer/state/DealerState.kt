package domain.gamer.state

import domain.card.Card
import domain.judge.Result

class DealerState(private val _cards: MutableList<Card> = mutableListOf()) : ParticipantState(_cards) {
    fun checkAvailableForPick() = calculateCardSum() <= CARD_PICK_CONDITION

    fun judgeDealerResult(playersResult: Map<String, Result>) = mutableListOf<Result>().apply {
        playersResult.forEach {
            add(it.value.reverseResult())
        }
    }

    companion object {
        private const val CARD_PICK_CONDITION = 16
    }
}
