package blackjack.domain.participant

import blackjack.domain.card.CardDeck
import blackjack.domain.data.ParticipantCards

class BettingPlayers(private val _players: List<BettingPlayer>) {
    val players = _players.toList()

    init {
        require(_players.size in MINIMUM_PLAYER..MAXIMUM_PLAYER) {
            "블랙잭은 최소 ${MINIMUM_PLAYER}명에서 최대 ${MAXIMUM_PLAYER}명의 플레이어가 참여 가능합니다. (현재 플레이어수 : ${_players.size}명)"
        }
    }

    fun drawAll(deck: CardDeck) {
        _players.forEach { it.draw(deck) }
    }

    fun getFirstOpenCards(): List<ParticipantCards> = _players.map { ParticipantCards(it.user.name, it.user.getCards()) }

    companion object {
        private const val MINIMUM_PLAYER = 1
        private const val MAXIMUM_PLAYER = 7
    }
}
