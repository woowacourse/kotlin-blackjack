package blackjack.fixture

import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit
import blackjack.domain.card.TrumpCard

fun trumpCardFixture(): List<TrumpCard> =
    listOf(
        TrumpCard(Denomination.KING, Suit.DIA),
        TrumpCard(Denomination.KING, Suit.HEART),
    )

fun blackJackCardFixture(): List<TrumpCard> =
    listOf(
        TrumpCard(Denomination.ACE, Suit.DIA),
        TrumpCard(Denomination.KING, Suit.HEART),
    )

fun bustTrumpCardFixture(): List<TrumpCard> =
    listOf(
        TrumpCard(Denomination.KING, Suit.DIA),
        TrumpCard(Denomination.KING, Suit.HEART),
        TrumpCard(Denomination.KING, Suit.SPADE),
    )

fun minCardFixture(): List<TrumpCard> =
    listOf(
        TrumpCard(Denomination.TWO, Suit.DIA),
        TrumpCard(Denomination.THREE, Suit.HEART),
    )
