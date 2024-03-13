package blackjack.model

class DealingShoe(cards: List<Card>) {
    private val cards = cards.toList()
    private var idx = 0

    fun initGame(
        dealer: Dealer,
        players: List<Player>,
    ) {
        players.forEach { player ->
            player.initCard()
        }
        dealer.initCard()
    }

    private fun Participant.initCard() {
        this.addCard(pickCard())
        this.addCard(pickCard())
    }

    private fun pickCard(): Card {
        if (idx == cards.size) {
            throw IllegalStateException(EXCEPTION_EMPTY_CARD_MESSAGE)
        }
        return cards[idx++]
    }

    infix fun giveCardTo(participant: Participant) {
        participant.addCard(pickCard())
    }

    companion object {
        const val EXCEPTION_EMPTY_CARD_MESSAGE = "모든 카드를 소진해 게임을 종료합니다."
    }
}
