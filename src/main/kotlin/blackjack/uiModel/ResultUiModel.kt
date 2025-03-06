package blackjack.uiModel

import blackjack.domain.person.Player
import blackjack.domain.state.ResultState

data class ResultUiModel(
    val name: String,
    val result: String,
) {
    companion object {
        fun create(results: Map<Player, ResultState>): List<ResultUiModel> {
            return results.map { result ->
                ResultUiModel(result.key.name, result.value.toUiModel())
            }
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
