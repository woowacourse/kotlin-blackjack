package blackjack.model

class Dealer(name: String = "딜러") : Participant(name, DealerStrengthPolicy()) {
    override fun isHitable(): Boolean {
        if (isBusted()) return false
        val score = cards.sum()
        return score < HITABLE_THRESHOLD
    }

    companion object {
        private const val HITABLE_THRESHOLD = 17
    }
}
