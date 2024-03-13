package blackjack.model

enum class Result(val label: String) {
    DEALER_WIN("패"),
    PLAYER_WIN("승"),
    TIE("무"),
}
