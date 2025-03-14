package blackjack.model

interface Participant {
    val items: Items

    fun draw(cardDeck: CardDeck) {
        items.hand.add(cardDeck.draw())
    }

    fun getHandSize(): Int = items.hand.getHandCount()

    fun getScore(): Int = items.hand.score()

    fun isBusted(): Boolean = items.hand.isBust()

    fun compareHand(other: Participant): WinningResult

    fun addPrize(prize: Money) = items.money.addMoney(prize)
}
