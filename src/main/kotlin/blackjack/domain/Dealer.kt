package blackjack.domain

class Dealer(name: String = "딜러") : User(name) {
    override val isContinue: Boolean // dealer
        get() = Score(cards).checkDealerScore
}
