package blackjack.fixture

import blackjack.domain.card.Shape
import blackjack.domain.card.Tier
import blackjack.domain.card.TrumpCard

fun trumpCardFixture(): List<TrumpCard> =
    listOf(
        TrumpCard(Tier.KING, Shape.DIA),
        TrumpCard(Tier.KING, Shape.HEART),
    )

fun blackJackCardFixture(): List<TrumpCard> =
    listOf(
        TrumpCard(Tier.ACE, Shape.DIA),
        TrumpCard(Tier.KING, Shape.HEART),
    )

fun bustTrumpCardFixture(): List<TrumpCard> =
    listOf(
        TrumpCard(Tier.KING, Shape.DIA),
        TrumpCard(Tier.KING, Shape.HEART),
        TrumpCard(Tier.KING, Shape.SPADE),
    )

fun minCardFixture(): List<TrumpCard> =
    listOf(
        TrumpCard(Tier.TWO, Shape.DIA),
        TrumpCard(Tier.THREE, Shape.HEART),
    )
