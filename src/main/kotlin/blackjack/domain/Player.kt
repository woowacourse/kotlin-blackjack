package blackjack.domain

class Player(
    val name: String,
    hand: Hand = Hand(),
) : Participant() {
    var state: ParticipantState = ParticipantState.PLAYING
        private set

    override val onBusted: () -> Unit = {
        state = ParticipantState.LOSE
    }

    fun setResult(dealerScore: Int) {
        state =
            when {
                score > dealerScore -> ParticipantState.WIN
                score < dealerScore -> ParticipantState.LOSE
                else -> ParticipantState.DRAW
            }
    }

    fun win() {
        state = ParticipantState.WIN
    }
}
