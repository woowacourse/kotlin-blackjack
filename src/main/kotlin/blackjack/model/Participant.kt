package blackjack.model

abstract class Participant(
    val name: String,
    val hand: Hand = Hand(emptyList()),
) {
    fun pickCard(
        cardDeck: CardDeck,
        times: Int = 1,
    ) {
        repeat(times) {
            val card = cardDeck.pickCard()
            hand.add(card)
        }
    }

    fun getScore(): Int = hand.getScore()

    fun isBlackjack(): Boolean = hand.status == CardsStatus.BLACKJACK

    fun isBust(): Boolean = hand.status == CardsStatus.BUST

    abstract fun canHit(): Boolean

    abstract fun gainMoney(money: Money)

    abstract fun lossMoney(money: Money)
}
