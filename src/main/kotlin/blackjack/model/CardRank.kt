package blackjack.model

enum class CardRank {
    ACE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    QUEEN,
    KING,
    JACK,
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
