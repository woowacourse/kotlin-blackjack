package domain

class User(val name: String, val cards: Cards, val betAmount: Double) {

    lateinit var gameResult: GameResult

    companion object {
        fun create(userBetAmount: UserBetAmount, cards: Cards): User =
            User(userBetAmount.userName, cards, userBetAmount.betAmount.toDouble())
    }
}
