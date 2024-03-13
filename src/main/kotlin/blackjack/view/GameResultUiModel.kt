package blackjack.view

import blackjack.model.participant.GameResult
import blackjack.view.DealerResultUiModel.Companion.DEFAULT_COUNT

data class PlayerResultUiModel(
    val name: String,
    val result: GameResult,
)

data class DealerResultUiModel(
    val name: String,
    val winCount: Int,
    val drawCount: Int,
    val loseCount: Int,
) {
    companion object {
        const val DEFAULT_COUNT = 0
    }
}

fun GameResult.toUiModelWith(name: String): PlayerResultUiModel {
    return PlayerResultUiModel(name, this)
}

fun Map<GameResult, Int>.toUiModelWith(name: String): DealerResultUiModel {
    return DealerResultUiModel(
        name = name,
        winCount = getOrDefault(GameResult.WIN, DEFAULT_COUNT),
        drawCount = getOrDefault(GameResult.DRAW, DEFAULT_COUNT),
        loseCount = getOrDefault(GameResult.LOSE, DEFAULT_COUNT),
    )
}
