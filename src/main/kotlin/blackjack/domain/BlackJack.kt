package blackjack.domain

class BlackJack(private val deck: CardDeck, private val participants: Participants) {
    fun drawAll() {
        participants.drawAll(deck)
    }

    fun getFirstOpenCards(): List<ParticipantCards> = participants.getFirstOpenCards()

    fun getPlayers(): List<Player> = participants.getPlayers()

    fun drawPlayer(player: Player) {
        player.addCard(deck.draw())
    }

    fun drawDealer(block: (Boolean) -> Unit) = participants.drawDealerCard(deck, block)

    fun getCards(): List<ParticipantCards> = participants.getCards()

    fun getTotalScores(): List<ParticipantScore> = participants.getTotalScores()

    fun getGameResults(): PlayerResults = participants.judgePlayers()

    companion object {
        private const val BLACKJACK_SCORE = 21
        fun blackjackScore(): Int = BLACKJACK_SCORE
    }
}
