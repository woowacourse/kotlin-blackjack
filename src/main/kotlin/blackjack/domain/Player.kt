package blackjack.domain

class Player(
    val name: String,
) : Participant() {
    override val onBusted: () -> Unit = { state = ParticipantState.LOSE }

    var state: ParticipantState = ParticipantState.PLAYING
        private set

    fun setResult(dealerScore: Score) {
        this.state =
            when {
                score > dealerScore -> ParticipantState.WIN
                score < dealerScore -> ParticipantState.LOSE
                else -> ParticipantState.DRAW
            }
    }

    val hittable: Boolean get() = score is Score.Hittable && score.value != Score.SCORE_MAX_CAN_HAVE

    fun win() {
        state = ParticipantState.WIN
    }
}
