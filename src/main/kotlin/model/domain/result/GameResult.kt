package model.domain.result

enum class GameResult(private val label: String) {
    WIN("승"),
    LOSE("패"),
    DRAW("무"),
}
