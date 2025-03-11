package blackjack.domain

class Player(
    val name: String,
) {
    private val onBusted: () -> Unit = { state = PlayerState.LOSE }
    private val hand: Hand = Hand(onBusted)

    var state: PlayerState = PlayerState.PLAYING
        private set
    val cards: List<Card> = hand.cards
    val score: Int = hand.score

    fun draw(card: Card) {
        hand.draw(card)
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
