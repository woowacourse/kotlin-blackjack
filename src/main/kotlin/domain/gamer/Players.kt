package domain.gamer

import domain.deck.Deck
import domain.judge.PlayerResultInfo
import domain.judge.Result

class Players(val players: List<Player>) {
    init {
        require(players.size <= MAX_PLAYER_COUNT) { println(ERROR_OVER_MAX_PLAYER_COUNT) }
    }

    fun makeStartDecks(deck: Deck) {
        players.forEach {
            it.makeStartDeck(deck)
        }
    }

    fun getPlayersWinningResult(dealer: Dealer): Map<Player, Result> = mutableMapOf<Player, Result>().apply {
        players.forEach {
            this[it] = it.judgeResult(dealer.cards)
        }
    }.toMap()

    fun getPlayersReward(playersResult: Map<Player, PlayerResultInfo>): MutableMap<String, Int> =
        mutableMapOf<String, Int>().apply {
            playersResult.map {
                put(it.key.name, it.value.calculateRevenue(it.key.cards.checkBlackjack()))
            }
        }

    companion object {
        private const val ERROR_OVER_MAX_PLAYER_COUNT = "게임 인원은 8명까지 가능합니다"
        private const val MAX_PLAYER_COUNT = 8
    }
}
