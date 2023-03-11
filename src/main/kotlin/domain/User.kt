package domain

class User(
    name: String,
    cards: Cards,
    val betAmount: Double
) : Player(name, cards) {

    private lateinit var _gameResult: GameResult

    val gameResult: GameResult
        get() =
            if (!::_gameResult.isInitialized) {
                throw IllegalStateException(GAME_RESULT_UNINITIALIZED)
            } else _gameResult

    fun setGameResult(gameResult: GameResult) {
        _gameResult = gameResult
    }

    companion object {

        private const val GAME_RESULT_UNINITIALIZED = "[ERROR] 아직 유저의 게임 결과가 계산되지 않았습니다."
        fun create(userBetAmount: UserBetAmount, cards: Cards): User =
            User(userBetAmount.userName, cards, userBetAmount.betAmount.toDouble())
    }
}
