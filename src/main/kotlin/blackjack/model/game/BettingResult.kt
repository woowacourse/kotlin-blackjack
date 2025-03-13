package blackjack.model.game

import blackjack.model.participant.Money
import blackjack.model.participant.Name

@JvmInline
value class BettingResult(
    val bettingResult: Map<Name, Money>,
)
