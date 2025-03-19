package blackjack.view

import blackjack.model.domain.card.Card
import blackjack.model.domain.card.CardNumber
import blackjack.model.domain.card.Shape
import blackjack.model.domain.participant.Dealer
import blackjack.model.domain.participant.Participants
import blackjack.model.domain.participant.PlayerBetResult

class OutputView {
    fun printInitCardStatus(
        dealer: Dealer,
        players: List<Participants>,
    ) {
        val playerName = players.joinToString { it.name }
        println(OUTPUT_DISTRIBUTE_CARD.format(dealer.name, playerName))
        printPlayerInitCard(listOf(dealer) + players)
    }

    private fun printPlayerInitCard(players: List<Participants>) {
        players.forEach { player ->
            println(PLAYER_STATUS.format(player.name + CARD, displayCard(player.showStartCards())))
        }
        println()
    }

    fun printCardStatus(player: Participants) {
        println(makeFormat(player))
    }

    fun printNoMoreCards() {
        println(OUTPUT_NO_MORE_CARDS_MESSAGE)
    }

    private fun makeFormat(player: Participants): String {
        return PLAYER_STATUS.format(player.name + CARD, displayCard(player.cardDeck))
    }

    private fun displayCard(cards: List<Card>): String {
        return cards.joinToString { CARD_FORMAT.format(it.cardNumber.display(), it.symbol.toKorean()) }
    }

    fun printDealerReceiveCard(
        count: Int,
        dealer: Dealer,
    ) {
        println()
        repeat(count) {
            println(OUTPUT_DEALER_RECEIVE_CARD.format(dealer.name))
        }
        println()
    }

    fun participantsCardResult(participants: List<Participants>) {
        participants.forEach { participant ->
            println(makeFormat(participant) + OUTPUT_PARTICIPANTS_CARD_RESULT.format(participant.sumCardNumber))
        }
    }

    fun participantsMoneyResult(playersBetResult: List<PlayerBetResult>) {
        println(FINAL_RESULT)
        playersBetResult.forEach { (player, betAmount) ->
            println(PLAYER_STATUS.format(player.name, formatNumber(betAmount)))
        }
        println()
    }

    private fun formatNumber(value: Float): String {
        return if (value % 1.0 == 0.0) {
            value.toInt().toString()
        } else {
            value.toString()
        }
    }

    private fun Shape.toKorean(): String {
        return when (this) {
            Shape.Heart -> "하트"
            Shape.Spade -> "스페이드"
            Shape.Diamond -> "다이아몬드"
            Shape.Clover -> "클로버"
        }
    }

    private fun CardNumber.display(): String {
        return when (this) {
            CardNumber.Ace -> "A"
            CardNumber.King -> "K"
            CardNumber.Queen -> "Q"
            CardNumber.Jack -> "J"
            else -> "${this.number}"
        }
    }

    companion object {
        private const val OUTPUT_DISTRIBUTE_CARD: String = "\n%s와 %s에게 2장의 나누었습니다."
        private const val OUTPUT_DEALER_RECEIVE_CARD: String = "%s는 16이하라 한장의 카드를 더 받았습니다."
        private const val OUTPUT_NO_MORE_CARDS_MESSAGE: String = "버스트가 되어서 카드를 더 뽑을 수 없습니다."
        private const val OUTPUT_PARTICIPANTS_CARD_RESULT: String = " - 결과: %d"
        private const val FINAL_RESULT: String = "\n## 최종 승패"
        private const val PLAYER_STATUS: String = "%s: %s"
        private const val CARD: String = "카드"
        private const val CARD_FORMAT: String = "%s%s"
    }
}
