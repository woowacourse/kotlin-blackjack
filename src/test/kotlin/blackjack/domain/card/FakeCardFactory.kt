package blackjack.domain.card

class FakeCardFactory(private val cards: List<TrumpCard>) : CardFactory {
    override fun makeCard(): List<TrumpCard> {
        return cards
    }
}
