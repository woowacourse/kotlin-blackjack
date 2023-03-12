package blackjack.domain.participant

import blackjack.domain.card.CardDeck
import blackjack.domain.data.ParticipantCards

class Players(private val _users: List<Player>) {
    val users = _users.toList()

    init {
        require(_users.size in MINIMUM_PLAYER..MAXIMUM_PLAYER) {
            "블랙잭은 최소 ${MINIMUM_PLAYER}명에서 최대 ${MAXIMUM_PLAYER}명의 플레이어가 참여 가능합니다. (현재 플레이어수 : ${_users.size}명)"
        }
    }

    fun drawAll(deck: CardDeck) {
        _users.forEach { it.addCard(deck.draw()) }
    }

    fun getFirstOpenCards(): List<ParticipantCards> = _users.map { ParticipantCards(it.name, it.getCards()) }

    companion object {
        private const val MINIMUM_PLAYER = 1
        private const val MAXIMUM_PLAYER = 7
    }
}
