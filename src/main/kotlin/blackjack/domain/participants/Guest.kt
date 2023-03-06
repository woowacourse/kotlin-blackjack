package blackjack.domain.participants

class Guest(name: String) : User(name) {
    override val isContinue: Boolean
        get() = isNotBust && isBlackJack.not()

    private val isNotBust: Boolean
        get() = Score(cards).minScore <= BLACKJACK_NUMBER

    companion object {
        private const val BLACKJACK_NUMBER = 21
    }
}
