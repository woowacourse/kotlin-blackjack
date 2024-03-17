package blackjack.model.participant

import blackjack.model.deck.Card
import blackjack.model.deck.Deck
import blackjack.model.deck.HandCards2
import blackjack.model.participant.testState.Finish

class Dealer2 private constructor() {
    private val handCards = HandCards2()

    fun getFirstCard() = handCards.cards.first()

    fun initCards(cards: List<Card>) {
        require(cards.size == INIT_CARD_AMOUNT) {
            "초기 카드는 2장으로 제한됩니다."
        }
        handCards.initCard(cards)
    }

    fun playTurn(
        cards: (Int) -> List<Card>,
        showResult: () -> Unit,
    ) {
        while (handCards.calculateScore() < DEALER_HIT_THRESHOLD) {
            handCards.playTurn(true, cards)
            showResult.invoke()
        }
    }

    fun gameResult(playersState: Map<Player2, Finish>): Map<String, CompetitionResult> {
        val result = mutableMapOf<String, CompetitionResult>()
        playersState.entries.forEach { (player, finish) ->
            result[player.name] = finish.getResult(player.getScore(), handCards.calculateScore())
        }
        return result
    }

    fun getAllCards() = handCards.cards

    fun getScore() = handCards.calculateScore()

    companion object {
        private const val DEALER_HIT_THRESHOLD = 17
        private const val INIT_CARD_AMOUNT = 2

        fun withInitCards(deck: Deck): Dealer2 {
            return Dealer2().also { it.initCards(deck.draw(INIT_CARD_AMOUNT)) }
        }
    }
}
