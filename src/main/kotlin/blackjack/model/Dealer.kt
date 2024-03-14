package blackjack.model

class Dealer(name: String = "딜러") : Participant(name, DealerStrengthPolicy()) {
    override fun isHitable(): Boolean {
        val score = cards.sum()
        return score < DEALER_HITABLE_THRESHOLD
    }

    companion object {
        private const val DEALER_HITABLE_THRESHOLD = 17
    }
}
