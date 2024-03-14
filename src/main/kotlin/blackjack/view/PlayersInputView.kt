package blackjack.view

import blackjack.model.deck.Deck
import blackjack.model.participant.Players

class PlayersInputView {
    fun readPlayerNames(deck: Deck): Players {
        println(PLAYERS_INPUT_MESSAGE)
        return runCatching {
            val names =
                readln().split(SPLIT_DELIMITER)
                    .map { it.trim() }
                    .filterNot { it.isBlank() }
            Players.playerNamesOf(names, deck)
        }.onFailure {
            println(it.message)
            return readPlayerNames(deck)
        }.getOrThrow()
    }

    companion object {
        private const val SPLIT_DELIMITER = ","
        private const val PLAYERS_INPUT_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    }
}
