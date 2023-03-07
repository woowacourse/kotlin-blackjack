package blackjack.domain

class Dealer(name: String = "딜러") : Participant(name) {
    fun shouldHit(): Boolean = cards.size == INIT_CARD_SIZE && score <= HIT_STANDARD_SCORE

    companion object {
        const val HIT_STANDARD_SCORE = 16
    }
}
