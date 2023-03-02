package blackjack.domain

class Dealer(name: String = "딜러") : Participant(name) {
    fun shouldHit(): Boolean = cards.size == INIT_CARD_SIZE && getScore() <= HIT_STANDARD_SCORE

    companion object {
        private const val HIT_STANDARD_SCORE = 16
    }
}
