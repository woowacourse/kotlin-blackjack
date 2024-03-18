package blackjack.view

import blackjack.model.Profit

data class GameResultUiModel(
    val name: String,
    val profit: Int,
)

fun Profit.toUiModelWith(name: String): GameResultUiModel {
    return GameResultUiModel(name, amount)
}
