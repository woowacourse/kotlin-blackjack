package blackjack.domain

class Player(
    val name: String,
) : Playable {
    override val cards: List<Card> get() = hand.cards
    override val score: Score get() = hand.score
    var state: ParticipantState = ParticipantState.PLAYING
        private set
    val canHit: Boolean get() = hand.canHit

    private val hand: Hand = Hand(onBusted = { state = ParticipantState.LOSE })

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
