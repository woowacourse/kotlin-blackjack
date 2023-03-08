package blackjack.domain

class BettingPlayers(private val players: List<BettingPlayer>) {
    init {
        require(players.size in MINIMUM_PLAYER..MAXIMUM_PLAYER) {
            "블랙잭은 최소 ${MINIMUM_PLAYER}명에서 최대 ${MAXIMUM_PLAYER}명의 플레이어가 참여 가능합니다. (현재 플레이어수 : ${players.size}명)"
        }
    }

    fun drawAll(deck: CardDeck) {
        players.forEach { it.draw(deck) }
    }

    fun getFirstOpenCards(): List<ParticipantCards> = getCards()

    fun getCards(): List<ParticipantCards> = players.map { it.getCards() }

    fun getTotalScores(): List<ParticipantScore> = players.map { it.getParticipantScore() }

    fun toList(): List<BettingPlayer> = players.toList()

    companion object {
        private const val MINIMUM_PLAYER = 1
        private const val MAXIMUM_PLAYER = 7
    }
}
