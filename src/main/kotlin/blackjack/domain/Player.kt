package blackjack.domain

class Player(
    val name: String,
) : Participant {
    private val _cards = mutableListOf<TrumpCard>()
    override val cards: List<TrumpCard> get() = _cards.toList()

    override fun drawCard(card: TrumpCard) {
        _cards.add(card)
    }
}
