package blackjack.model

class Dealer(name: String = "딜러") : Participant(name, DealerStrengthPolicy()) {
    override fun isHitable(): Boolean {
        val score = cards.sum()
        val threshold = 17
        return score < threshold
    }
}
