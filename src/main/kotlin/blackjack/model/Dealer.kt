package blackjack.model

class Dealer(name: String = "딜러") : Participant(name, DealerStrengthPolicy()) {
    override fun initialCardsList(): List<Card> {
        return cards.toList().take(1)
    }

    override fun isHitable(): Boolean {
        val score = cards.scoreSum()
        return score < HITABLE_THRESHOLD
    }

    companion object {
        private const val HITABLE_THRESHOLD = 17
    }
}
