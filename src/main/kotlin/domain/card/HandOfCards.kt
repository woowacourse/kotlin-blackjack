package domain.card

class HandOfCards(cards: List<Card>) {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card> get() = _cards.toList()

    init {
        require(cards.size == 2) { "[ERROR] 2장의 카드가 들어오지 않았습니다. 들어온 카드 수: ${cards.size}" }
        this._cards.addAll(cards)
    }

    constructor(card1: Card, card2: Card) : this(listOf(card1, card2))

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun countAce() = cards.count { it.number == CardNumber.ACE }

    fun getExceptAceSum() = cards
        .filter { it.number != CardNumber.ACE }
        .sumOf { it.number.value }

    fun showFirstCard(): List<Card> {
        return cards.subList(0, 1)
    }
}
