package blackjack.domain.model

enum class ActionType {
    Hit,
    Stay,
    ;

    companion object {
        fun get(yesOrNo: Boolean): ActionType {
            if (yesOrNo) return Hit
            return Stay
        }
    }
}
