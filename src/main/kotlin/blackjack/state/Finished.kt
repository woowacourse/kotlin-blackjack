package blackjack.state

abstract class Finished : State {
    override fun decisionState(): State {
        return this
    }
}
