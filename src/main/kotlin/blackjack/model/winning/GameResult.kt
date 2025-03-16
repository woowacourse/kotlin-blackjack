package blackjack.model.winning

import blackjack.model.participant.Name

class GameResult(
    val playersResult: PlayersResult,
    val dealerResult: DealerResult,
) {
    @JvmInline
    value class PlayersResult(
        val value: Map<Name, WinningState>,
    )

    @JvmInline
    value class DealerResult(
        val value: Map<WinningState, WinningCount>,
    )
}
