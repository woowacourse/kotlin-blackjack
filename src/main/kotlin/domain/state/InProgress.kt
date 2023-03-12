package domain.state

abstract class InProgress : State {
    override val isFinished = false
    override fun toStay() = Stay(handOfCards)
    override fun playerProfit(other: State, bet: Double) = throw IllegalStateException("아직 진행중입니다")
}
