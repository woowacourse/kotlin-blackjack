package blackjack.model.domain

enum class ActionType {
    Hit,
    Stay,
    ;

    companion object {
        fun get(yesOrNo: YesOrNo): ActionType {
            if (yesOrNo.input in listOf("y", "Y")) return Hit
            return Stay
        }
    }
}
