package blackjack.domain

class Player(val name: String, override val cardBunch: CardBunch) : Participant {

    override fun isOverCondition(): Boolean = cardBunch.isBurst()
}
