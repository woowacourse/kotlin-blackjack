package model

import model.GameResult.Companion.BLACKJACK_SCORE

class Player(val name: String, private val hand: Hand) : Participant(hand) {
    override fun decideToHit(): Boolean = getTotalScore() <= BLACKJACK_SCORE

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
