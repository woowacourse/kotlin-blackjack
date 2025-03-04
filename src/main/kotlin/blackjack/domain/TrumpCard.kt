package blackjack.domain

import blackjack.domain.enums.CardTier
import blackjack.domain.enums.Shape

data class TrumpCard(
    val tier: CardTier,
    val shape: Shape,
)
