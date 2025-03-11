package blackjack.model.game

import blackjack.model.rule.WinningResult

class GameResult(
    val dealerResult: Map<WinningResult, Int>,
    val playerResults: Map<String, WinningResult>,
)
