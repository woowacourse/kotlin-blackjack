package blackjack.domain

class Player(
    val name: String,
) : Participant() {
    var state: PlayerState = PlayerState.PLAYING
        private set

    override val onBusted: () -> Unit = {
        state = PlayerState.LOSE
    }

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
