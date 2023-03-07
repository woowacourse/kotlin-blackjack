package blackjack.domain

class BlackJack(private val deck: CardDeck, private val participants: Participants) {
    fun drawAll() {
        participants.drawAll(deck)
    }

    fun getFirstOpenCards(): Map<String, List<Card>> = participants.getFirstOpenCards()

    fun getPlayers(): List<Player> = participants.getPlayers()

    fun drawPlayer(player: Player) {
        player.addCard(deck.draw())
    }

    fun drawDealer(block: (Boolean) -> Unit) = participants.drawDealerCard(deck, block)

    fun getCards(): Map<String, List<Card>> = participants.getCards()

    fun getTotalScores(): Map<String, Int> = participants.getTotalScores()

    fun getGameResults(): PlayerResults = participants.judgePlayers()

    companion object {
        private const val BLACKJACK_SCORE = 21
        fun blackjackScore(): Int = BLACKJACK_SCORE
    }
}
