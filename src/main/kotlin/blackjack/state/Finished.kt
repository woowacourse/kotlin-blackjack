package blackjack.state

abstract class Finished : State {
    abstract val rate: Double

    override fun decisionState(): State {
        return this
    }

    fun profit(money: Int): Double = money * rate
}
