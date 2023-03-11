package blackjack.domain.player

import blackjack.domain.Result
import blackjack.domain.card.Cards

class Dealer(name: String = "딜러", cards: Cards = Cards()) : Player(name, cards) {

    override fun checkProvideCardPossible(): Boolean = (cards.calculateScore() <= PARTICIPANT_MORE_CARD_CRITERIA)

    fun calculateResults(participantsResults: ParticipantsResults): DealerResult {
        val results: MutableList<Result> = mutableListOf()
        participantsResults.results.map { participantResult ->
            results.add(participantResult.result.reverse())
        }
        return DealerResult(results)
    }

    companion object {
        const val PARTICIPANT_MORE_CARD_CRITERIA = 16
    }
}
