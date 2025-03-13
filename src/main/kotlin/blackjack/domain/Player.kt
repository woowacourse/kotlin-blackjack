package blackjack.domain

class Player(
    val name: String,
) : Playable {
    private val hand: Hand = Hand(onBusted = { state = ParticipantState.LOSE })
    var state: ParticipantState = ParticipantState.PLAYING
        private set

    val cards: List<Card> get() = hand.cards
    val canHit: Boolean get() = hand.canHit
    val score: Score get() = hand.score

    fun setResult(dealerScore: Score) {
        this.state =
            when {
                score > dealerScore -> ParticipantState.WIN
                score < dealerScore -> ParticipantState.LOSE
                else -> ParticipantState.DRAW
            }
    }

    fun win() {
        state = ParticipantState.WIN
    }

    fun draw(card: Card) {
        hand.draw(card)
    }
}
