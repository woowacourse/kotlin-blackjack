package blackjack.model

class Items(
    val hand: Hand,
    val money: Money,
) {
    fun setNewHand(newHand: Hand) {
        hand.clear()
        newHand.cards.forEach { card ->
            hand.add(card)
        }
    }
}
