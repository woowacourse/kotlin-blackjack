package blackjack.domain.participants

class Guest(name: String, money: Int = 0) : User(name, money) {
    override val isContinue: Boolean
        get() = isNotBust && isBlackJack().not()

    private val isNotBust: Boolean
        get() = cards.result.minScore <= BLACKJACK_NUMBER

    companion object {
        private const val BLACKJACK_NUMBER = 21
    }
}
