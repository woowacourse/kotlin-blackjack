package blackjack.fixture

import blackjack.domain.card.Shape
import blackjack.domain.card.Tier
import blackjack.domain.card.TrumpCard

fun trumpCardFixture(): List<TrumpCard> =
    listOf(
        TrumpCard(Tier.KING, Shape.DIA),
        TrumpCard(Tier.KING, Shape.HEART),
    )
