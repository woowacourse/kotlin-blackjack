package blackjack.view.model

data class PlayerUiModel(
    override val profit: Double,
    val name: String,
) : UiModel
