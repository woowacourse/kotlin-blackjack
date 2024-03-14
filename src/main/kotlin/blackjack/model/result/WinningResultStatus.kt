package blackjack.model.result

enum class WinningResultStatus {
    VICTORY,
    DEFEAT,
    DRAW
    ;

    fun reverse(): WinningResultStatus =
        when (this) {
            VICTORY -> DEFEAT
            DEFEAT -> VICTORY
            DRAW -> DRAW
        }
}
