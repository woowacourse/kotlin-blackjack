package blackjack.view

import blackjack.model.Deck
import blackjack.model.Players

class PlayersInputView {
    fun readPlayerNames(deck: Deck): Players {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return Players.playerNamesOf(readln().split(SPLIT_DELIMITER), deck)
    }

    companion object {
        private const val SPLIT_DELIMITER = ","
    }
}
