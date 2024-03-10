package blackjack.view

import blackjack.model.deck.Deck
import blackjack.model.participant.Players

class PlayersInputView {
    fun readPlayerNames(deck: Deck): Players {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return retryWhileNoException(deck)
    }

    private fun retryWhileNoException(deck: Deck) =
        runCatching {
            val names =
                readln().splitNames()
            Players.playerNamesOf(names, deck)
        }.onFailure {
            println(it.message)
            return readPlayerNames(deck)
        }.getOrThrow()

    private fun String.splitNames() =
        split(SPLIT_DELIMITER)
            .map { it.trim() }
            .filterNot { it.isBlank() }

    companion object {
        private const val SPLIT_DELIMITER = ","
    }
}
