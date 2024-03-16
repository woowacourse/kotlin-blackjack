package blackjack.state

abstract class Finished : State {
    abstract val rate: Double

    override fun drawCard(): State {
        throw IllegalStateException(ERROR_DRAW_MESSAGE)
    }

    fun profit(money: Int): Double = money * rate

    companion object {
        private const val ERROR_DRAW_MESSAGE = "게임이 종료되어 더이상 카드를 받을 수 없습니다."
    }
}
