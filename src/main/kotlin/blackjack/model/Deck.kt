package blackjack.model

class Deck(cardList: List<Card>) {
    private val deck: MutableList<Card> = cardList.toMutableList()

    constructor() : this(oneDeck.shuffled())

    fun pop() = deck.removeFirstOrNull() ?: throw NoSuchElementException("덱을 모두 사용했습니다.")

    companion object {
        private val oneDeck =
            Suit.entries.flatMap { suit ->
                productNumberAndSuit(suit)
            }

        private fun productNumberAndSuit(suit: Suit) = CardNumber.entries.map { cardNumber -> Card(cardNumber, suit) }
    }
}
