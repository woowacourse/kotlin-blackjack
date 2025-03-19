package blackjack.uimodel

import blackjack.domain.GameResult
import blackjack.domain.participants.Player

data class ResultUiModel(
    val name: String,
    val profit: Double,
) {
    companion object {
        fun create(gameResult: GameResult): List<ResultUiModel> {
            return gameResult.results.map { (participant, profitAmount) ->
                ResultUiModel(
                    name = (participant as? Player)?.name ?: "딜러",
                    profit = profitAmount.value,
                )
            }
        }
    }
}
