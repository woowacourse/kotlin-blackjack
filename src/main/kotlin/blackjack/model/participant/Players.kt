package blackjack.model.participant

import blackjack.model.BattingMoney
import blackjack.model.Result
import blackjack.model.deck.Card

class Players(playersInfo: Map<String, BattingMoney>, cards: (Int) -> List<Card>) {
    val gamePlayers: List<Player>

    init {
        require(playersInfo.size in MIN_PLAYER_COUNT..MAX_PLAYER_COUNT) { "${MIN_PLAYER_COUNT}명 이상 ${MAX_PLAYER_COUNT}명 이하의 플레이어만 가능합니다." }
        gamePlayers =
            playersInfo.map { (playerName, battingMoney) ->
                Player.withInitCards(playerName, battingMoney, cards)
            }
    }

    fun playTurn(
        cards: (Int) -> List<Card>,
        isHit: (String) -> Boolean,
        playResult: (Player) -> Unit,
    ): List<Result> {
        return gamePlayers.map { player ->
            Result(player, player.playTurn(cards, isHit, playResult))
        }
    }

    companion object {
        private const val MIN_PLAYER_COUNT = 1
        private const val MAX_PLAYER_COUNT = 6
    }
}
