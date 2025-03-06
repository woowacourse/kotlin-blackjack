package blackjack.fixture

import blackjack.domain.TrumpCard
import blackjack.domain.enums.CardTier
import blackjack.domain.enums.Shape

fun trumpCardFixture(): List<TrumpCard> =
    listOf(
        TrumpCard(CardTier.K, Shape.Dia),
        TrumpCard(CardTier.K, Shape.Heart),
    )
