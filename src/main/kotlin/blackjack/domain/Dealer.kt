package blackjack.domain

class Dealer : Player("딜러") {
    fun isStay(): Boolean = hand.calculateTotalScore() >= STAY_SCORE

    companion object {
        private const val STAY_SCORE = 17
    }
}
