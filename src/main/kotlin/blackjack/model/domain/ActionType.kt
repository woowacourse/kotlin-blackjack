package blackjack.model.domain

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
