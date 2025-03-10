package blackjack.domain

class Player(
    val name: String,
) {
    private val hand: Hand = Hand(emptyList())
    val cards: List<Card>
        get() = hand.value
    var wantToHit: Boolean? = null
    var playerState: PlayerState = PlayerState.PLAYING

    fun getCard(card: Card) {
        hand.add(card)
    }

    fun getCards(cards: List<Card>) {
        hand.addAll(*(cards.toTypedArray()))
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
