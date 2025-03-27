package blackjack.model.participant

import blackjack.model.card.CardDeck

class Players(
    val value: List<Player>,
) {
    init {
        require(value.size in MIN_PLAYERS..MAX_PLAYERS) { ERROR_MESSAGE_NUMBER_OF_PEOPLE }
    }

    fun giveCardForPlayer(cardDeck: CardDeck) {
        value.forEach { player ->
            player.receiveCard(cardDeck.pickCard())
        }
    }

    companion object {
        const val MIN_PLAYERS = 1
        const val MAX_PLAYERS = 8
        const val ERROR_MESSAGE_NUMBER_OF_PEOPLE = "플레이어는 1명에서 8명까지 게임에 참여가능합니다."

        fun from(vararg names: String): Players = Players(names.map { name -> Player(name = name) })
    }
}
