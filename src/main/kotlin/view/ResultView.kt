package view

import domain.BlackJackCardDeck
import domain.Card
import domain.CardCategory
import domain.CardNumber
import domain.Dealer
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
        return "${numberInfo()}${categoryInfo()}"
    }

    private fun Card.numberInfo(): String {
        return when (cardNumber) {
            CardNumber.ACE -> ACE
            CardNumber.KING -> KING
            CardNumber.QUEEN -> QUEEN
            CardNumber.JACK -> JACK
            else -> cardNumber.value.toString()
        }
    }

    private fun Card.categoryInfo(): String {
        return when (cardCategory) {
            CardCategory.CLOVER -> CLOVER_SHAPE
            CardCategory.DIAMOND -> DIAMOND_SHAPE
            CardCategory.HEART -> HEART_SHAPE
            CardCategory.SPADE -> SPADE_SHAPE
        }
    }

    companion object {
        private const val PRINT_GAME_INIT_MESSAGE = "\n딜러와 %s에게 ${BlackJackCardDeck.DRAW_INIT_CARD_COUNT}장의 나누었습니다."
        private const val SEPARATOR = ", "
        private const val PRINT_NAME_AND_CARDS = "%s카드: %s"
        private const val PRINT_DEALER_ADD_CARD = "\n%s는 ${Dealer.DEALER_ADD_CARD_CONDITION}이하라 한장의 카드를 더 받았습니다."
        private const val PRINT_GAME_RESULT = "\n## 최종승패"
        private const val PRINT_BET_GAME_RESULT = "%s: %d"
        private const val PRINT_NAME_AND_CARDS_AND_SCORE = "%s 카드: %s - 결과: %d"

        private const val ACE = "A"
        private const val KING = "K"
        private const val QUEEN = "Q"
        private const val JACK = "J"

        private const val CLOVER_SHAPE = "클로버"
        private const val DIAMOND_SHAPE = "다이아"
        private const val HEART_SHAPE = "하트"
        private const val SPADE_SHAPE = "스페이드"
    }
}
