package blackjack.model.winning

import blackjack.model.participant.Name

@JvmInline
value class PlayersResult(
    val value: Map<Name, WinningState> = emptyMap(),
)
