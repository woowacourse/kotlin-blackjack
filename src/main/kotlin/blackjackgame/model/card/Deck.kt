package blackjackgame.model.card

class Deck(val cards: MutableList<Card> = shuffledCards()) {

    companion object {
        fun shuffledCards(): MutableList<Card> {
            return CARDS.shuffled().toMutableList()
        }
    }
}