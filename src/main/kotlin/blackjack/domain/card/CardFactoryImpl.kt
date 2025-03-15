package blackjack.domain.card

fun cardFactoryImpl() =
    CardFactory {
        Shape.entries
            .flatMap { shape ->
                Denomination.entries.map { tier ->
                    TrumpCard(tier, shape)
                }
            }.shuffled()
    }
