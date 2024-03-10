package blackjack.model.util

enum class CardNumber(val value: String, val score: Int) {
    TWO("2", 2),
    THIRD("3", 3),
    FOURTH("4", 4),
    FIFTH("5", 5),
    SIXTH("6", 6),
    SEVENTH("7", 7),
    EIGHTH("8", 8),
    NINTH("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10),
    ACE("A", 1),
}
