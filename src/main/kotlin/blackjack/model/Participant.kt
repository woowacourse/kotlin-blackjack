package blackjack.model

abstract class Participant(
    val name: String,
    val cards: Cards = Cards(emptyList()),
) {
    fun pickCard(
        cardDeck: CardDeck,
        times: Int = 1,
    ) {
        repeat(times) {
            val card = cardDeck.pickCard()
            cards.add(card)
        }
    }

    fun isBlackjack(): Boolean = cards.status == CardsStatus.BLACKJACK

    fun isBust(): Boolean = cards.status == CardsStatus.BUST

    abstract fun gainMoney(money: Money)

    abstract fun lossMoney(money: Money)
}
