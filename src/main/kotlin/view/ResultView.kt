package view

import domain.*

class ResultView {
    fun printGameInit(players: Players) {
        println(PRINT_GAME_INIT_MESSAGE.format(formatStringName(players)))
    }

    private fun formatStringName(players: Players): String {
        return players.list.joinToString(SEPARATOR) { it.name.name }
    }

    fun printInitCards(participants: Participants) {
        participants.participants.forEach { participant ->
            println(PRINT_NAME_AND_CARDS.format(participant.name.name, formatStringCards(participant.showInitCards())))
        }
        println()
    }

    fun printPlayerCard(player: Player) {
        println(PRINT_NAME_AND_CARDS.format(player.name.name, formatStringCards(player.cards.list)))
    }

    fun printDealerAddCard(dealer: Dealer) {
        println(PRINT_DEALER_ADD_CARD.format(dealer.name.name))
    }

    fun printGameResult(gameResult: GameResult) {
        println(PRINT_GAME_RESULT)
        formatStringDealerResult(gameResult)
        formatStringPlayersResult(gameResult)
    }

    private fun formatStringCards(cards: List<Card>): String {
        return cards.joinToString(SEPARATOR) { card ->
            card.info()
        }
    }

    private fun Card.info(): String {
        return "${cardNumber.number}${cardCategory.pattern}"
    }

    private fun formatStringDealerResult(gameResult: GameResult) {
        print("딜러: ")
        gameResult.getDealerGameResult().forEach { (gameResult, count) ->
            print(PRINT_DEALER_GAME_RESULT.format(count, gameResult.name))
        }
        println()
    }

    private fun formatStringPlayersResult(gameResult: GameResult) {
        gameResult.getPlayersGameResult().forEach { (name, gameResult) ->
            println(PRINT_PLAYER_GAME_RESULT.format(name.name, gameResult.name))
        }
    }

    fun printScore(participants: Participants) {
        participants.participants.forEach { participant ->
            println(
                PRINT_NAME_AND_CARDS_AND_SCORE.format(
                    participant.name.name,
                    formatStringCards(participant.cards.list),
                    participant.getScore().getValue()
                )
            )
        }
    }

    companion object {
        private const val PRINT_GAME_INIT_MESSAGE = "\n딜러와 %s에게 ${RandomCardDrawer.DRAW_INIT_CARD_COUNT}장의 나누었습니다."
        private const val SEPARATOR = ", "
        private const val PRINT_NAME_AND_CARDS = "%s카드: %s"
        private const val PRINT_DEALER_ADD_CARD = "\n%s는 ${Dealer.DEALER_ADD_CARD_CONDITION}이하라 한장의 카드를 더 받았습니다."
        private const val PRINT_GAME_RESULT = "\n## 최종승패"
        private const val PRINT_DEALER_GAME_RESULT = "%d%s "
        private const val PRINT_PLAYER_GAME_RESULT = "%s: %s"
        private const val PRINT_NAME_AND_CARDS_AND_SCORE = "%s 카드: %s - 결과: %d"
    }
}

