package blackjack.fixture

import blackjack.domain.CardTier
import blackjack.domain.Shape
import blackjack.domain.TrumpCard

fun trumpCardFixture(): List<TrumpCard> =
    listOf(
        TrumpCard(CardTier.KING, Shape.DIA),
        TrumpCard(CardTier.KING, Shape.HEART),
    )
