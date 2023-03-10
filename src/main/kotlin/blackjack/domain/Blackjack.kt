package blackjack.domain

class Blackjack(private val deck: CardDeck, private val participants: Participants) {
    constructor(deck: CardDeck, players: List<Participant>) : this(deck, Participants(listOf(Dealer()) + players))

    fun start(
        onStartFirstDrawn: (Participants) -> Unit,
        onFirstDrawn: (Participant) -> Unit,
        onDrawnMore: (Participant) -> Unit,
        onEndGame: (BlackjackResult) -> Unit,
    ) {
        participants.drawFirst(deck, onStartFirstDrawn, onFirstDrawn)
        participants.takeTurns(deck, onDrawnMore)
        onEndGame(BlackjackResult(participants.getCardResults(), participants.getMatchResults()))
    }
}
