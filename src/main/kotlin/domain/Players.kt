package domain

class Players(val players: List<Player>) {
    init {
        check(players.size in 1..8){
            "[ERROR] 플레이어는 최소 1명이상 8명이하여야 합니다."
        }
    }
}
