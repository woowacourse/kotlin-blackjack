package blackjack.domain

class Player(
    val name: String,
) : Participant() {
    override val onBusted: () -> Unit = { state = PlayerState.LOSE }

    var state: PlayerState = PlayerState.PLAYING
        private set

    fun setResult(dealerHandState: HandState) {
        this.state =
            when {
                handState > dealerHandState -> PlayerState.WIN
                handState < dealerHandState -> PlayerState.LOSE
                else -> PlayerState.DRAW
            }
    }

    fun canHitMore(): Boolean = handState is HandState.Score && score < 21

    fun win() {
        state = PlayerState.WIN
    }
}
