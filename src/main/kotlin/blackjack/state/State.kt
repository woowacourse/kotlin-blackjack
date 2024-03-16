package blackjack.state

interface State {
    fun drawCard(): State
}
