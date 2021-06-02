package blackjackgame.model.card

class Deck(val cards: MutableList<Card> = shuffledCards()) {

    fun handOutInitialCards(): List<Card> {
        require(cards.size >= 2) { "패가 부족합니다." }
        return listOf(cards.removeLast(), cards.removeLast())
    }

    fun handOutCard(): Card {
        require(cards.size >= 1) { "패가 부족합니다." }
        return cards.removeLast()
    }

    companion object {
        fun shuffledCards(): MutableList<Card> {
            return CARDS.shuffled().toMutableList()
        }
    }
}
