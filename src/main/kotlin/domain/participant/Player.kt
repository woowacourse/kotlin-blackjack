package domain.participant

import domain.card.Card

class Player(name: Name, bettingMoney: BettingMoney) : Participant(name, bettingMoney) {

    override fun showInitCards(): List<Card> {
        return state.cards().list.take(TAKE_TWO)
    }

    override fun isPossibleDrawCard(): Boolean {
        return !state.isFinished()
    }

    companion object {
        private const val TAKE_TWO = 2
    }
}
