package blackjack.domain

class BlackJack(private val deck: CardDeck, private val participants: Participants) {
    fun readyToStart() {
        participants.drawFirst(deck)
    }

    fun start(onDrawn: (Participant) -> Unit): BlackJackResult {
        participants.takeTurns(deck, onDrawn)
        return BlackJackResult(
            participants.getCardResults(),
            participants.getMatchResults(),
        )
    }

    fun getFirstOpenCards(): Map<String, List<Card>> = participants.getFirstOpenCards()
}
