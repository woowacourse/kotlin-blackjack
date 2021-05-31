package domain

class Player(val name: String, val hand: Hand = Hand()) {
    fun draw(card: Card) {
        hand.addCard(card)
    }

    fun score(): Int {
        return hand.getScore()
    }

    fun isBust(): Boolean {
        return hand.isBust()
    }

}
