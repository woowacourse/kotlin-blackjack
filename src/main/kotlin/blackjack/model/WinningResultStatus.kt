package blackjack.model

enum class WinningResultStatus(val output: String) {
    VICTORY("승"),
    DEFEAT("패"),
    DRAW("무"),
}
