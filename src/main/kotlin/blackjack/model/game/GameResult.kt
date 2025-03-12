package blackjack.model.game

import blackjack.model.participant.Name
import blackjack.model.rule.WinningResult

class GameResult(
    val dealerResult: Map<WinningResult, ResultCount>,
    val playerResults: Map<Name, WinningResult>,
)
