package domain.person

import domain.constant.BLACK_JACK
import domain.person.GameState.BUST
import domain.person.GameState.HIT

class Player(override val name: String) : Person(name) {

    override fun checkState(): GameState {
        if (calculateMinTotal() > BLACK_JACK) {
            return BUST
        }
        return HIT
    }

    private fun calculateMinTotal() = calculateSumExceptAce() + countAce()
}
