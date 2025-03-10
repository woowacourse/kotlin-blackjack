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
        hand.add(cards)
    }

    fun getCountOfCards(): Int = hand.getSize()

    fun hitOrStay(hit: () -> Unit) {
        if (wantToHit == true) {
            hit()
        }
    }

    fun setResult() {
        if (hand.getScore() == null) {
            playerState = PlayerState.LOSE
        }
    }

    fun getScore() = hand.getScore()
}
