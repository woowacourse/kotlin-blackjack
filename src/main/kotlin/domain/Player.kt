package domain

class Player(name: Name, cards: Cards) : Participant(name, cards) {
    override fun showInitCards(): List<Card> {
        return cards.list.take(TAKE_TWO)
    }

    override fun isPossibleDrawCard(): Boolean {
        return !cards.getScore().isBurst()
    }

    fun getGameResult(dealerScore: Score): GameResultType {
        val myScore = getScore()
        if (myScore.isBurst()) return GameResultType.LOSE
        if (dealerScore.isBurst()) return GameResultType.WIN
        return when (myScore.getValue() - dealerScore.getValue()) {
            DRAW_NUMBER -> GameResultType.DRAW
            in POSITIVE_RANGE -> GameResultType.WIN
            in NEGATIVE_RANGE -> GameResultType.LOSE
            else -> throw IllegalStateException(GAME_RESULT_ERROR)
        }
    }

    companion object {
        private const val TAKE_TWO = 2
        private const val GAME_RESULT_ERROR = "[ERROR] 승패 반환 오류가 발생하였습니다!!"
        private const val DRAW_NUMBER = 0
        private val POSITIVE_RANGE = 1..Int.MAX_VALUE
        private val NEGATIVE_RANGE = Int.MIN_VALUE..-1
    }
}
