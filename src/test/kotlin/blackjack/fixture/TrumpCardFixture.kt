package blackjack.fixture

import blackjack.domain.TrumpCard
import blackjack.domain.enums.CardTier
import blackjack.domain.enums.Shape

fun trumpCardFixture(): List<TrumpCard> =
    listOf(
        TrumpCard(CardTier.KING, Shape.DIA),
        TrumpCard(CardTier.KING, Shape.HEART),
    )
