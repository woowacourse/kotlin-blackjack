package blackjack.domain

class BlackJack(private val deck: CardDeck, private val participants: Participants) {
    fun drawAll() {
        participants.drawAll(deck)
    }

    fun getInitialHands() = participants.getInitialHands()

    fun getGameResults(): Map<String, String> = participants.getGameResults()
}
