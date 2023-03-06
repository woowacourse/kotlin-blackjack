package blackjack.domain.participants

class Dealer(name: String = "딜러") : User(name) {
    override val isContinue: Boolean
        get() = Score(cards).maxScore < DEALER_MIN_NUMBER

    companion object {
        private const val DEALER_MIN_NUMBER = 17
    }
}
