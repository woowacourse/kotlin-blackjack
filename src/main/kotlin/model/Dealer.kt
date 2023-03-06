package model

import model.Cards.Companion.DEALER_STANDARD_HIT_POINT

class Dealer(cards: Cards, name: Name = Name(DEALER)) : Participant(cards, name) {
    override fun isPossibleDrawCard(): Boolean = cards.sum() <= DEALER_STANDARD_HIT_POINT

    fun getGameResult(players: Players): Map<Result, Int> {
        val playersResult = players.getGameResult(this)
        return mapOf(
            Result.WIN to (playersResult.count { it.value == Result.LOSE }),
            Result.LOSE to (playersResult.count { it.value == Result.WIN })
        )
    }

    companion object {
        const val DEALER = "딜러"
    }
}
