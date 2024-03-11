package blackjack.model

enum class CardHandState(
    val precondition: Int = 0,
) {
    BLACKJACK(21),
    BURST,
    STAY,
    HIT,
}
