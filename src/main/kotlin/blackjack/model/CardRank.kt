package blackjack.model

enum class CardRank(
    name: String,
) {
    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    QUEEN("Q"),
    KING("K"),
    JACK("J"),
    ;

    companion object {
        fun CardRank.score(isAceFlipped: Boolean = false): Int =
            when (this) {
                ACE -> if (isAceFlipped) 1 else 11
                TWO -> 2
                THREE -> 3
                FOUR -> 4
                FIVE -> 5
                SIX -> 6
                SEVEN -> 7
                EIGHT -> 8
                NINE -> 9
                else -> 10
            }
    }
}
