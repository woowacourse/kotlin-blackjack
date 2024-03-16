package blackjack.state

interface State {
    fun decisionState(): State
}
