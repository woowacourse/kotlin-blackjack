package blackjack.domain.model

enum class ActionType {
    Hit,
    Stay,
    ;

    companion object {
        private const val YES: String = "y"
        private const val NO: String = "n"

        fun get(yesOrNo: String): ActionType {
            if (yesOrNo == YES) return Hit
            return Stay
        }
    }
}
