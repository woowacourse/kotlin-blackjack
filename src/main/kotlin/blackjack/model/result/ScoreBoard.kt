package blackjack.model.result

import blackjack.model.role.PlayerName

class ScoreBoard(private val record: Map<PlayerName, Score>) {
    fun calculatePlayerWinning(dealerScore: Score): PlayerWinning =
        PlayerWinning(
            record.mapValues { (_, playerScore) ->
                playerScore.determineWinning(dealerScore)
            },
        )
}
