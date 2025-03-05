package model

class Players(val players: List<Player>) {
    init {
        require(players.isNotEmpty()) { "게임을 시작하기 위해서는 플레이어는 1명 이상이어야 합니다" }
        require(players.distinct().size == players.size) { "플레이어의 이름은 중복될 수 없습니다" }
    }
}
