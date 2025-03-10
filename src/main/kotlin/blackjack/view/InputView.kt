package blackjack.view

import blackjack.domain.Player

class InputView {
    fun readPlayers(): List<Player> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val players: List<Player> = readln().split(",").map { name: String -> Player(name.trim()) }
        return players
        println()
    }
}
