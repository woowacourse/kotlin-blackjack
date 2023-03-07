package blackjack.domain.card

class CardDeck(cardsGenerator: CardsGenerator) {

    private val cards: MutableList<Card> = RandomCardsGenerator().generate().toMutableList()

    init {
        require(cards.size == 52) { "초기 카드는 52장이어야 합니다." }
    }

    fun checkProvidePossible(): Boolean {
        if (cards.isNotEmpty()) return true
        return false
    }

    fun provide(): Card {
        val card = cards[0]
        cards.removeAt(0)
        return card
    }
}
