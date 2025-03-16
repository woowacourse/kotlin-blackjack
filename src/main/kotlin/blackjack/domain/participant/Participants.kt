package blackjack.domain.participant

import blackjack.domain.deck.Deck

class Participants(
    val dealer: Dealer,
    val players: List<Player>,
) {
    init {
        require(players.size <= MAXIMUM_PLAYERS) { ERROR_OVER_MAX_PLAYERS_MESSAGE }
    }

    fun getChoice(
        deck: Deck,
        getPlayerChoice: (String) -> Boolean,
        onPlayerStateUpdated: (Player) -> Unit,
    ) {
        players.forEach { player ->
            player.choice(deck, getPlayerChoice, onPlayerStateUpdated)
        }
    }

    companion object {
        const val ERROR_OVER_MAX_PLAYERS_MESSAGE = "[ERROR] 최대 참가자 수인 7명을 초과하였습니다."

        const val MAXIMUM_PLAYERS = 7
    }
}
