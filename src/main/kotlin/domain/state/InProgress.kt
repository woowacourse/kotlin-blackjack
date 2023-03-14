package domain.state

abstract class InProgress : State {
    override fun playerProfit(other: State, bet: Double) = throw IllegalStateException("아직 진행중입니다")
}
