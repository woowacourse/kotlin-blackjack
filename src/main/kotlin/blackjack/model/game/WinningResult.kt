package blackjack.model.game

import blackjack.model.participant.Name

class WinningResult(
    val dealerResult: Map<WinningState, ResultCount>,
    val playerResults: Map<Name, WinningState>,
)
