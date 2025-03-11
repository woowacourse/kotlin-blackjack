package blackjack.domain

class Player(
    val name: String,
) : Participant() {
    override val onBusted: () -> Unit = { state = PlayerState.LOSE }

    var state: PlayerState = PlayerState.PLAYING
        private set

    fun setResult(dealerScore: Int) {
        state =
            when {
                score > dealerScore -> PlayerState.WIN
                score < dealerScore -> PlayerState.LOSE
                else -> PlayerState.DRAW
            }
    }

    fun win() {
        state = PlayerState.WIN
    }
}
