package blackjack.domain

class Dealer(override val cardBunch: CardBunch) : Participant {
    override fun isOverCondition(): Boolean = cardBunch.getTotalScore() > ADD_CARD_CONDITION

    companion object {
        private const val ADD_CARD_CONDITION = 16
    }
}
