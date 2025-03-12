package model

class Player(val name: String, private val hand: Hand) : Participant(hand) {
    override fun decideToHit(): Boolean = getScore() <= GameResultDecider.BLACKJACK_SCORE

    fun playTurn(
        shouldHit: (Player) -> Boolean,
        getCard: () -> List<Card>,
        showCards: () -> Unit,
    ) {
        while (decideToHit() && shouldHit(this)) {
            receiveCards { getCard() }
            showCards()
        }
    }
}
