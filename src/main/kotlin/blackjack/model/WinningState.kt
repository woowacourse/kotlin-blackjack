package blackjack.model

enum class WinningState {
    WIN,
    LOSS,
    DRAW,
    ;

    fun reversed(): WinningState {
        if (this == WIN) return LOSS
        if (this == LOSS) return WIN
        return DRAW
    }
}
