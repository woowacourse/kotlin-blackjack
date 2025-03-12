package blackjack.domain.card

fun fakeCardFactory(cards: List<TrumpCard>) =
    CardFactory {
        cards
    }
