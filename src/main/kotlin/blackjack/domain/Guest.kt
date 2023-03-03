package blackjack.domain

class Guest(name: String) : User(name) {
    override val isContinue: Boolean
        get() = Score(cards).minScore <= BlackJackGame.BLACKJACK_NUMBER
}
