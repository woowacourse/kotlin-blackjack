package blackjack.domain.card

fun cardFactoryImpl() =
    CardFactory {
        Shape.entries
            .flatMap { shape ->
                Tier.entries.map { tier ->
                    TrumpCard(tier, shape)
                }
            }.shuffled()
    }
