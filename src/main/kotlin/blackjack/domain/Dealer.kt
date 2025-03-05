package blackjack.domain

class Dealer : Participant {
    private val _cards = mutableListOf<TrumpCard>()
    override val cards: List<TrumpCard> get() = _cards.toList()

    override fun drawCard(card: TrumpCard) {
        _cards.add(card)
    }
}
