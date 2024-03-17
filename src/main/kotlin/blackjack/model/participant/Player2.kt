package blackjack.model.participant

import blackjack.model.deck.Card
import blackjack.model.deck.HandCards2
import blackjack.model.participant.testState.Finish
import blackjack.model.participant.testState.Gaming

class Player2(val name: String) {
    private val handCards = HandCards2()

    fun initCards(cards: List<Card>) {
        require(cards.size == 2) {
            "초기 카드는 2장으로 제한됩니다."
        }
        handCards.initCard(cards)
    }

    fun playTurn(
        cards: (Int) -> List<Card>,
        isHit: (String) -> Boolean,
        showResult: (Player2) -> Unit,
    ): Finish {
        while (handCards.state is Gaming) {
            handCards.playTurn(isHit(name), cards)
            showResult(this)
        }
        return handCards.state as Finish
    }

    fun getAllCards() = handCards.cards

    fun getScore() = handCards.calculateScore()
}
