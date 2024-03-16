package blackjack.model.winning

enum class WinningResultStatus {
    VICTORY,
    DEFEAT,
    PUSH,
    ;

    fun reverse(): WinningResultStatus =
        when (this) {
            VICTORY -> DEFEAT
            DEFEAT -> VICTORY
            PUSH -> PUSH
        }
}
