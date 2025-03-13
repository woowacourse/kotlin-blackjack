package blackjack.domain

class Player(
    val name: String,
    private val betting: Betting,
) : Playable {
    private val hand: Hand = Hand(onBusted = { state = ParticipantState.LOSE })
    override val cards: List<Card> get() = hand.cards
    override val score: Score get() = hand.score
    var state: ParticipantState = ParticipantState.PLAYING
        private set
    val canHit: Boolean get() = hand.canHit
    val profit: Int get() = betting.toProfit(state, score)

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
