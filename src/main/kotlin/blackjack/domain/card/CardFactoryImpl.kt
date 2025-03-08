package blackjack.domain.card

class CardFactoryImpl : CardFactory {
    override fun makeCard(): List<TrumpCard> {
        return Shape.entries
            .flatMap { shape ->
                Tier.entries.map { tier ->
                    TrumpCard(tier, shape)
                }
            }.shuffled()
    }
}
