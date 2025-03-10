package blackjack.domain

class Player(
    val name: String,
) {
    private val hand: Hand = Hand()
    val cards: List<Card>
        get() = hand.value
    var wantToHit: Boolean? = null
    var playerState: PlayerState = PlayerState.PLAYING

    fun draw(card: Card) {
        hand.draw(card)
    }

    fun hitOrStay(hit: () -> Unit) {
        if (wantToHit == true) {
            hit()
        }
    }

    fun setResult() {
        if (hand.score == null) {
            playerState = PlayerState.LOSE
        }
    }

    fun getScore() = hand.score
}
