package blackjack.domain

class Guest(name: String) : User(name) {
    override val isContinue: Boolean // dealer
        get() = Score(cards).isNotBust
}
