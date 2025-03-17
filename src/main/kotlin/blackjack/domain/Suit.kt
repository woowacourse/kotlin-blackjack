package blackjack.domain

enum class Suit(
    val displayName: String,
) {
    DIAMONDS("다이아몬드"),
    HEARTS("하트"),
    SPADES("스페이드"),
    CLUBS("클로버"),
    ;

    override fun toString(): String = displayName
}
