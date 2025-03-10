package blackjack.domain

class Player(
    val name: String,
    hand: Hand = Hand(),
) : Participant(hand) {
    override val onBusted: () -> Unit = {
        state = ParticipantState.LOSE
    }
}
