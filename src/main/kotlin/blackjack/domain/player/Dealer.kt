package blackjack.domain.player

import blackjack.domain.Result

class Dealer(name: String = "딜러") : Player(name) {

    val results: MutableMap<Result, Int> = Result.values().associateWith { 0 }.toMutableMap()

    fun checkMustGenerateCard(): Boolean {
        if (cards.sumCardsNumber() <= MIN_SUM_NUMBER) return true
        return false
    }

    fun decideParticipantsResult(participants: Participants) {
        participants.values.forEach {
            it.updateResult(cards.sumCardsNumber())
        }
    }

    fun decideDealerResult(participants: Participants) {
        participants.values.forEach {
            val dealerResult = Result.reverse(it.result)
            results[dealerResult] = results[dealerResult]?.plus(1) ?: throw IllegalArgumentException()
        }
    }

    companion object {
        const val MIN_SUM_NUMBER = 16
    }
}
