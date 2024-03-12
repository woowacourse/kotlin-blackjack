package blackjack.model.winning

enum class WinningResultStatus(val output: String) {
    VICTORY("승"),
    DEFEAT("패"),
    DRAW("무"),
    ;

    fun reverse(): WinningResultStatus =
        when (this) {
            VICTORY -> DEFEAT
            DEFEAT -> VICTORY
            DRAW -> DRAW
        }
}
