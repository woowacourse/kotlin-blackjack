package blackjack.model

enum class CardNumber(val label: String, val point: Int) {
    ACE("1", 1),
    TWO("2", 2),
    Three("3", 3),
    Four("4", 4),
    Five("5", 5),
    Six("6", 6),
    Seven("7", 7),
    Eight("8", 8),
    Nine("9", 9),
    Ten("10", 10),
    Jack("J", 10),
    Queen("Q", 10),
    King("K", 10),
}
