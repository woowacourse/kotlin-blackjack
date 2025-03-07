package blackjack.uiModel

import blackjack.domain.GameResult
import blackjack.domain.state.ResultState

data class ResultUiModel(
    val name: String,
    val result: String,
) {
    companion object {
        fun create(gameResult: GameResult): List<ResultUiModel> {
            return gameResult.winStatus.map { ResultUiModel(it.key.name, it.value.toUiModel()) }
        }

        private fun ResultState.toUiModel(): String {
            return when (this) {
                ResultState.WIN -> "승"
                ResultState.DRAW -> "무"
                else -> "패"
            }
        }
    }
}
