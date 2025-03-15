package blackjack.domain.card

fun cardFactoryImpl() =
    CardFactory {
        Suit.entries
            .flatMap { shape ->
                Denomination.entries.map { tier ->
                    TrumpCard(tier, shape)
                }
            }.shuffled()
    }
