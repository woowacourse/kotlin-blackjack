package blackjack.model

enum class CardHandState(
    val precondition: Int = 0,
) {
    BLACKJACK(21),
    BUST,
    STAY,
    HIT,
}
