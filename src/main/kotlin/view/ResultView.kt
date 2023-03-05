package view

import domain.BlackJackCardDeck
import domain.Card
import domain.Dealer
import domain.GameResult
import domain.Participant
import domain.Player
import domain.Players

class ResultView {
    fun printGameInit(players: Players) {
        println(PRINT_GAME_INIT_MESSAGE.format(formatStringName(players)))
    }

    private fun formatStringName(players: Players): String {
        return players.list.joinToString(SEPARATOR) { it.name.value }
    }

    fun printInitCards(participants: List<Participant>) {
        participants.forEach { participant ->
            println(PRINT_NAME_AND_CARDS.format(participant.name.value, formatStringCards(participant.showInitCards())))
        }
        println()
    }

    fun printPlayerCard(player: Player) {
        println(PRINT_NAME_AND_CARDS.format(player.name.value, formatStringCards(player.showAllCards())))
    }

    fun printDealerAddCard(dealer: Dealer) {
        println(PRINT_DEALER_ADD_CARD.format(dealer.name.value))
    }

    fun printGameResult(players: Players, dealer: Dealer) {
        println(PRINT_GAME_RESULT)
        println(formatToStringDealerResultType(players, dealer))
        printPlayersResult(players, dealer)
    }

    private fun formatToStringDealerResultType(players: Players, dealer: Dealer): String {
        val dealerResult = dealer.getResult(players)
        return PRINT_DEALER_GAME_RESULT.format(
            dealer.name.value,
            formatToStringDealerResultType(dealerResult[GameResult.WIN] ?: 0, PRINT_WIN),
            formatToStringDealerResultType(dealerResult[GameResult.DRAW] ?: 0, PRINT_DRAW),
            formatToStringDealerResultType(dealerResult[GameResult.LOSE] ?: 0, PRINT_LOSE),
        )
    }

    private fun formatToStringDealerResultType(resultCount: Int, gameResultType: String): String {
        if (resultCount == 0) return ""
        return "$resultCount$gameResultType "
    }

    private fun printPlayersResult(players: Players, dealer: Dealer) {
        players.list.forEach { player ->
            println(formatToStringPlayerResult(player, dealer))
        }
    }

    private fun formatToStringPlayerResult(player: Player, dealer: Dealer): String {
        return when (player.getGameResult(dealer)) {
            GameResult.WIN -> PRINT_PLAYER_GAME_RESULT.format(player.name.value, PRINT_WIN)
            GameResult.DRAW -> PRINT_PLAYER_GAME_RESULT.format(player.name.value, PRINT_DRAW)
            GameResult.LOSE -> PRINT_PLAYER_GAME_RESULT.format(player.name.value, PRINT_LOSE)
        }
    }

    fun printScore(participants: List<Participant>) {
        participants.forEach { participant ->
            println(
                PRINT_NAME_AND_CARDS_AND_SCORE.format(
                    participant.name.value,
                    formatStringCards(participant.showAllCards()),
                    participant.resultSum()
                )
            )
        }
    }

    private fun formatStringCards(cards: List<Card>): String {
        return cards.joinToString(SEPARATOR) { card ->
            card.info()
        }
    }

    private fun Card.info(): String {
        return "${cardNumber.number}${cardCategory.pattern}"
    }

    companion object {
        private const val PRINT_GAME_INIT_MESSAGE = "\n딜러와 %s에게 ${BlackJackCardDeck.DRAW_INIT_CARD_COUNT}장의 나누었습니다."
        private const val SEPARATOR = ", "
        private const val PRINT_NAME_AND_CARDS = "%s카드: %s"
        private const val PRINT_DEALER_ADD_CARD = "\n%s는 ${Dealer.DEALER_ADD_CARD_CONDITION}이하라 한장의 카드를 더 받았습니다."
        private const val PRINT_GAME_RESULT = "\n## 최종승패"
        private const val PRINT_DEALER_GAME_RESULT = "%s: %s%s%s"
        private const val PRINT_PLAYER_GAME_RESULT = "%s: %s"
        private const val PRINT_NAME_AND_CARDS_AND_SCORE = "%s 카드: %s - 결과: %d"

        private const val PRINT_WIN = "승"
        private const val PRINT_DRAW = "무"
        private const val PRINT_LOSE = "패"
    }
}
