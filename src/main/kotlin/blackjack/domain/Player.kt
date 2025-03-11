package blackjack.domain

class Player(
    val name: String,
    hand: Hand = Hand(),
) : Participant(hand) {
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
}
