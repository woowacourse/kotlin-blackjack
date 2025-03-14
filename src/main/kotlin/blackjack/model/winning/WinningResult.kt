package blackjack.model.winning

import blackjack.model.participant.Name

class WinningResult(
    val dealerResult: Map<WinningState, Count>,
    val playerResults: Map<Name, WinningState>,
)
