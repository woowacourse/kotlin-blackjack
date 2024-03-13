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

    companion object {
        fun from(compared: Int): WinningState {
            return when {
                compared == 0 -> DRAW
                compared > 0 -> WIN
                else -> LOSS
            }
        }
    }
}
