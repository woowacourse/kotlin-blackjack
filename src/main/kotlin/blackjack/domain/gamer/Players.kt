package blackjack.domain.gamer

class Players(val players: List<Player>) {
    fun isRemainToHit(): Boolean {
        return players.any { it.isAbleToHit() }
    }

    fun currentPlayer(): Player {
        return players.find { it.isAbleToHit() }
            ?: throw IllegalArgumentException("게임을 진행할 수 있는 플레이어가 없습니다.")
    }
}