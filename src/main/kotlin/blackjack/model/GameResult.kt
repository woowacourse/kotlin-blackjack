package blackjack.model

enum class GameResult(
    val koreanTitle: String,
) {
    PUSH("무"),
    WIN("승"),
    LOSE("패"),
}
