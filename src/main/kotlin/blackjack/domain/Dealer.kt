package blackjack.domain

class Dealer(name: String) : User(name) {
    override val isContinue: Boolean // dealer
        get() = Score(cards).checkDealerScore
}
