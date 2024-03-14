package blackjack.model

enum class WinningState(val label: String) {
    WIN("승"),
    LOSS("패"),
    DRAW("무"),
    ;

    fun reversed(): WinningState {
        if (this == WIN) return LOSS
        if (this == LOSS) return WIN
        return DRAW
    }
}
