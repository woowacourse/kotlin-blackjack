package blackjack.domain

class Players(private val players: List<Player>) {
    init {
        require(players.size in MINIMUM_PLAYER..MAXIMUM_PLAYER) {
            "블랙잭은 최소 ${MINIMUM_PLAYER}명에서 최대 ${MAXIMUM_PLAYER}명의 플레이어가 참여 가능합니다. (현재 플레이어수 : ${players.size}명)"
        }
    }

    fun drawAll(deck: CardDeck) {
        players.forEach { it.addCard(deck.draw()) }
    }

    fun getFirstOpenCards(): Map<String, List<Card>> = players.associate { it.name to it.getFirstOpenCards() }

    fun getCards(): Map<String, List<Card>> = players.associate { it.name to it.getCards() }

    fun toList(): List<Player> = players.toList()

    operator fun get(index: Int): Player = players[index]

    companion object {
        private const val MINIMUM_PLAYER = 1
        private const val MAXIMUM_PLAYER = 7
    }
}
