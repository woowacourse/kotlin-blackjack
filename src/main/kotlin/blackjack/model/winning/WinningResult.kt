package blackjack.model.winning

import blackjack.model.participant.Name

class WinningResult(
    val dealerResult: Map<WinningState, WinningCount>,
    val playerResults: Map<Name, WinningState>,
)
