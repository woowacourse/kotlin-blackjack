package blackjack.domain.participants

class Dealer(name: String = "딜러", money: Int = 0) : User(name, money) {
    override val isContinue: Boolean
        get() = cards.result.maxScore < DEALER_MIN_NUMBER

    companion object {
        private const val DEALER_MIN_NUMBER = 17
    }
}
