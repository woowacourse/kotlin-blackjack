package blackjack.domain

class BlackJack(private val deck: CardDeck, private val participants: Participants) {
    fun readyToStart() {
        participants.drawFirst(deck)
    }

    fun start(onDrawn: (Participant) -> Unit) { // BlackJackResult {
        participants.takeTurns(deck, onDrawn)
    }

    fun getFirstOpenCards(): Map<String, List<Card>> = participants.getFirstOpenCards()

    // fun getPlayers(): List<Participant> = participants.getPlayers()

    // fun getDealer(): Participant = participants.getDealer()

    fun drawDealer(isDraw: (Boolean) -> Unit) = participants.drawDealerCard(deck, isDraw)

    fun getCards(): Map<String, List<Card>> = participants.getCards()

    fun getTotalScores(): Map<String, Int> = participants.getTotalScores()

    fun getGameResults(): PlayerResults = participants.judgePlayers()

    companion object {
        private const val BLACKJACK_SCORE = 21
        fun blackjackScore(): Int = BLACKJACK_SCORE
    }
}
