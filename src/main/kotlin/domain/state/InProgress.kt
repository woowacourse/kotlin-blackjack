package domain.state

abstract class InProgress : State {
    override val isFinished = false
    override fun toStay() = Stay()
    override fun profit() = throw IllegalStateException("아직 진행중입니다")
}
