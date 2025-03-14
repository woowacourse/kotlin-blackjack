package blackjack.model.betting

import blackjack.model.participant.Money
import blackjack.model.participant.Name

@JvmInline
value class BettingResult(
    val value: Map<Name, Money>,
)
