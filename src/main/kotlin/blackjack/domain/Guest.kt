package blackjack.domain

class Guest(name: String) : User(name) {
    override val isContinue: Boolean
        get() = isNotBust && isBlackJack.not()

    private val isNotBust: Boolean
        get() = Score(cards).minScore <= BlackJackGame.BLACKJACK_NUMBER
}
