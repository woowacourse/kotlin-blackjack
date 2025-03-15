package blackjack.fixture

import blackjack.domain.card.Denomination
import blackjack.domain.card.Shape
import blackjack.domain.card.TrumpCard

fun trumpCardFixture(): List<TrumpCard> =
    listOf(
        TrumpCard(Denomination.KING, Shape.DIA),
        TrumpCard(Denomination.KING, Shape.HEART),
    )

fun blackJackCardFixture(): List<TrumpCard> =
    listOf(
        TrumpCard(Denomination.ACE, Shape.DIA),
        TrumpCard(Denomination.KING, Shape.HEART),
    )

fun bustTrumpCardFixture(): List<TrumpCard> =
    listOf(
        TrumpCard(Denomination.KING, Shape.DIA),
        TrumpCard(Denomination.KING, Shape.HEART),
        TrumpCard(Denomination.KING, Shape.SPADE),
    )

fun minCardFixture(): List<TrumpCard> =
    listOf(
        TrumpCard(Denomination.TWO, Shape.DIA),
        TrumpCard(Denomination.THREE, Shape.HEART),
    )
