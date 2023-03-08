package blackjack.domain

// class Participants(private val dealer: Dealer, private val players: Players) {
class Participants(private val participants: List<Participant>) {
    init {
        require(participants.size in MINIMUM_PARTICIPANTS..MAXIMUM_PARTICIPANTS) {
            "블랙잭은 최소 ${MINIMUM_PARTICIPANTS}명에서 최대 ${MAXIMUM_PARTICIPANTS}명의 플레이어가 참여 가능합니다. (현재 플레이어수 : ${participants.size}명)"
        }
    }

    fun drawFirst(deck: CardDeck) {
        participants.forEach { it.addCard(deck.draw()) }
    }

    fun getFirstOpenCards(): Map<String, List<Card>> = participants.associate { it.name to it.getFirstOpenCards() }

    fun getPlayers(): List<Participant> = participants.filterIsInstance<Player>()

    private fun getDealer(): Participant = participants.first { it is Dealer }

    fun drawDealerCard(deck: CardDeck, isDraw: (Boolean) -> Unit) {
        while (getDealer().canDraw()) {
            getDealer().addCard(deck.draw())
            isDraw(true)
        }
        isDraw(false)
    }

    fun getCards(): Map<String, List<Card>> = participants.associate { it.name to it.getCards() }

    fun getTotalScores(): Map<String, Int> = participants.associate { it.name to it.getTotalScore() }

    fun judgePlayers(): PlayerResults = PlayerResults(
        getPlayers().associate { player ->
            player.name to (player judge getDealer())
        }
    )

    companion object {
        private const val MINIMUM_PARTICIPANTS = 1
        private const val MAXIMUM_PARTICIPANTS = 8
    }
}
