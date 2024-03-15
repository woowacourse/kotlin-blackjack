package blackjack.model.card

class Deck {
    // 내부적으로 수정 가능한 카드 리스트
    private val _cards: MutableList<Card> = mutableListOf()

    // 외부에 제공될 불변 리스트 뷰
    val cards: List<Card> get() = _cards.toList()

    init {
        if (_cards.isEmpty()) {
            initializeDeck()
        }
    }

    private fun initializeDeck() {
        _cards.clear()
        _cards.addAll(
            Suit.entries.flatMap { suit ->
                Denomination.entries.map { denomination ->
                    Card(denomination, suit)
                }
            },
        )
        _cards.shuffle()
    }

    fun doubleDealCard() = listOf(dealCard(), dealCard())

    fun dealCard(): Card = if (_cards.isNotEmpty()) _cards.removeAt(0) else throw NoSuchElementException("Deck is empty")
}
