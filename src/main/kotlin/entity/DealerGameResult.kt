package entity

class DealerGameResult(val value: Map<GameResultType, Int>, val profit: Money) {
    init {
        check(value.none { it.value == 0 }) { GAME_RESULT_COUNT_ERROR_MESSAGE }
    }

    companion object {
        private const val GAME_RESULT_COUNT_ERROR_MESSAGE = "게임 결과가 잘못되었습니다."
    }
}
