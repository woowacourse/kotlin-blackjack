package domain

class User(val name: String, val cards: Cards, val betAmount: Double) {

    lateinit var gameResult: GameResult

    companion object {
        fun create(nameAndBetAmount: Pair<String, Int>, cards: Cards): User =
            User(nameAndBetAmount.first, cards, nameAndBetAmount.second.toDouble())
    }
}
