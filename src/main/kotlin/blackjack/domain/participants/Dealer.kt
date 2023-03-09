package blackjack.domain.participants

class Dealer(name: String = "딜러") : User(name) {
    override val isContinuable: Boolean
        get() = cards.result.maxScore < DEALER_MIN_NUMBER

    companion object {
        private const val DEALER_MIN_NUMBER = 17
    }
}
