package blackjack.model

class Players(
    val value: List<Player>,
) {
    init {
        require(value.size in 1..7) {
            "[ERROR] 플레이어 수는 1명 이상부터 7명 이하만 가능합니다. 입력값: ${value.size}"
        }
        require(value.distinct().size == value.size) {
            "[ERROR] 플레이어 이름은 중복될 수 없습니다. 입력값: ${value.joinToString { ", " }}"
        }
    }

    fun results(dealerScore: Int): Map<String, WinningResult> =
        value.associate { player ->
            player.name to WinningResult.from(player.hand.score(), dealerScore)
        }

    fun scores(): List<Int> = value.map { player -> player.hand.score() }

    companion object {
        fun from(players: List<String>): Players = Players(players.map { name -> Player(name) })
    }
}
