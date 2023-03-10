package domain.gamer

import domain.deck.Deck
import domain.judge.PlayerResultInfo
import domain.judge.Result

class Players(private val players: List<Player>) {
    init {
        require(players.size <= MAX_PLAYER_COUNT) { println(ERROR_OVER_MAX_PLAYER_COUNT) }
    }

    fun getPlayers(): List<Player> {
        return players.toList()
    }

    fun makeStartDecks(deck: Deck) {
        players.forEach {
            it.makeStartDeck(deck)
        }
    }

    fun getPlayersWinningResult(dealer: Dealer) = mutableMapOf<String, Result>().apply {
        players.forEach {
            this[it.name] = it.judgeResult(dealer.cards)
        }
    }

    fun getPlayersReward(playersResult: Map<String, PlayerResultInfo>): MutableMap<String, Int> =
        mutableMapOf<String, Int>().apply {
            playersResult.map {
                put(it.key, it.value.calculateRevenue())
            }
        }

    companion object {
        private const val ERROR_OVER_MAX_PLAYER_COUNT = "게임 인원은 8명까지 가능합니다"
        private const val MAX_PLAYER_COUNT = 8
    }
}
