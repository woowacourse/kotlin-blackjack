package blackjack.domain.participants

class Guest(name: String, bettingMoney: Int = 10) : User(name) {
    val bettingMoney: Money = Money(bettingMoney)

    override val isContinue: Boolean
        get() = isNotBust && isBlackJack.not()

    private val isNotBust: Boolean
        get() = cards.result.minScore <= BLACKJACK_NUMBER

    companion object {
        private const val BLACKJACK_NUMBER = 21
    }
}
