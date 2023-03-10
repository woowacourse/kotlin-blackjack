package domain.state

abstract class Finished : State {
    override val isFinished = true
    override fun toStay() = throw IllegalStateException("이미 끝났습니다. stay를 호출할 수 없습니다.")
}
