package model

import model.Cards.Companion.DEALER_STANDARD_HIT_POINT

class Dealer(cards: Cards, name: Name = Name(DEALER)) : Participant(cards, name) {
    override fun getFirstOpenCards(): Cards = Cards(setOf(cards.firstCard()))

    override fun isPossibleDrawCard(): Boolean = cards.sum() <= DEALER_STANDARD_HIT_POINT

    override fun isHit(needToDraw: (String) -> Boolean): Boolean = isPossibleDrawCard()

    fun getFinalResult(participants: Participants): Map<Result, Int> {
        return mapOf(
            Result.WIN to participants.participants.count { getGameResult(it) == Result.WIN },
            Result.LOSE to participants.participants.count { getGameResult(it) == Result.LOSE }
        )
    }

    companion object {
        const val DEALER = "딜러"
    }
}
