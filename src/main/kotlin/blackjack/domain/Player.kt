package blackjack.domain

class Player(val name: String, override val cardBunch: CardBunch, val bettingMoney: BettingMoney) : Participant {
    override fun canHit(): Boolean = !cardBunch.isBurst()

    override fun getScore(): Int = cardBunch.getSumOfCards()
}
