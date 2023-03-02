package blackjack.domain

class Dealer(override val cardBunch: CardBunch) : Participant {
    fun isOverCondition(): Boolean = cardBunch.getTotalScore() > 16
}
