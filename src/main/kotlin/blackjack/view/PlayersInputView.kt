package blackjack.view

import blackjack.model.deck.Deck
import blackjack.model.participant.Players

class PlayersInputView {
    fun read(deck: Deck): Players =
        retryWhileNotException {
            println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
            val names =
                readln().splitNames()
            Players.withInitCards(names, deck)
        }

    private fun String.splitNames() =
        split(SPLIT_DELIMITER)
            .map { it.trim() }
            .filterNot { it.isBlank() }

    companion object {
        private const val SPLIT_DELIMITER = ","
    }
}
