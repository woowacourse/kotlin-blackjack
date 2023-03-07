package blackjack.domain

class Player(val name: String, override val cardBunch: CardBunch) : Participant {
    override fun canGetCard(): Boolean = !cardBunch.isBurst()

    override fun getScore(): Int = cardBunch.getSumOfCards()
}
