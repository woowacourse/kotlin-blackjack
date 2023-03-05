package domain.person

import domain.constant.BLACK_JACK

class Player(override val name: String) : Person(name) {

    override fun checkState() {
        if (calculateMinTotal() > BLACK_JACK) gameState = GameState.BUST
    }

    private fun calculateMinTotal() = calculateSumExceptAce() + countAce()

    fun rejectReceiveCard() {
        gameState = GameState.STAND
    }
}
