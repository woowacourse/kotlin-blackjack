package blackjack.model

interface Participant {
    val items: Items

    fun draw(card: Card) {
        items.hand.add(card)
    }

    fun getHandSize(): Int = items.hand.getHandCount()

    fun getScore(): Int = items.hand.score()

    fun isBusted(): Boolean = items.hand.isBust()

    fun addPrize(prize: Money) = items.money + prize

    fun changeHand(hand: Hand) {
        items.setNewHand(hand)
    }
}
