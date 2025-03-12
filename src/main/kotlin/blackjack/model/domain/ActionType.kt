package blackjack.model.domain

enum class ActionType {
    Hit,
    Stay,
    ;

    companion object {
        private val yesValidInput: List<String> = listOf("y", "Y")

        fun get(yesOrNo: String): ActionType {
            if (yesOrNo in yesValidInput) return Hit
            return Stay
        }
    }
}
