package blackjackgame.model.card

enum class Denomination(val value: String, val score: Int) {
    ACE("A", 1), KING("K", 10), JACK("J", 10),
    QUEEN("Q", 10), TWO("2", 2), THREE("3", 3),
    FOUR("4", 4), FIVE("5", 5), SIX("6", 6), SEVEN("7", 7),
    EIGHT("8", 8), NINE("9", 9), TEN("10", 10)
}
