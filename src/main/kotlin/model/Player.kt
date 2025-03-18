package model

import model.GameResult.Companion.BLACKJACK_SCORE

class Player(val name: String, private val hand: Hand) : Participant(hand) {
    override fun decideToHit(): Boolean = getTotalScore() <= BLACKJACK_SCORE

    var decisionMaker: () -> Boolean = { false }

    tailrec fun playTurn(
        getCard: () -> List<Card>,
        showCards: () -> Unit,
    ) {
        if (!decideToHit() || !decisionMaker()) return
        receiveCards { getCard() }
        showCards()
        playTurn(getCard, showCards)
    }
}
