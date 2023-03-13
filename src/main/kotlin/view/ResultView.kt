package view

import domain.card.Card
import domain.card.RandomCardPicker
import domain.participant.Dealer
import domain.participant.Participants
import domain.participant.Player
import domain.participant.Players

class ResultView {
    fun printGameInit(players: Players) {
        println(PRINT_GAME_INIT_MESSAGE.format(formatStringName(players)))
    }

    fun printInitCards(participants: Participants) {
        participants.list.forEach { participant ->
            println(PRINT_NAME_AND_CARDS.format(participant.name.value, formatStringCards(participant.showInitCards())))
        }
        println()
    }

    fun printPlayerCard(player: Player) {
        println(PRINT_NAME_AND_CARDS.format(player.name.value, formatStringCards(player.state.cards().list)))
    }

    fun printDealerAddCard(dealer: Dealer) {
        println(PRINT_DEALER_ADD_CARD.format(dealer.name.value))
    }

    fun printScore(participants: Participants) {
        println()
        participants.list.forEach { participant ->
            println(
                PRINT_NAME_AND_CARDS_AND_SCORE.format(
                    participant.name.value,
                    formatStringCards(participant.state.cards().list),
                    participant.state.cards().getScore().getValue(),
                ),
            )
        }
    }

    fun printParticipantsProfit(dealerProfit: Pair<Dealer, Double>, playersProfit: Map<Player, Double>) {
        println(PRINT_PROFIT_INIT)
        formatStringDealerProfit(dealerProfit)
        formatStringPlayersProfit(playersProfit)
    }

    private fun formatStringName(players: Players): String {
        return players.list.joinToString(SEPARATOR) { it.name.value }
    }

    private fun formatStringCards(cards: List<Card>): String {
        return cards.joinToString(SEPARATOR) { card ->
            card.info()
        }
    }

    private fun Card.info(): String {
        return "${cardNumber.number}${cardCategory.pattern}"
    }

    private fun formatStringDealerProfit(dealerProfit: Pair<Dealer, Double>) {
        print(PRINT_PROFIT.format(dealerProfit.first.name.value, dealerProfit.second))
        println()
    }

    private fun formatStringPlayersProfit(playersProfit: Map<Player, Double>) {
        playersProfit.forEach { (player, profit) ->
            println(PRINT_PROFIT.format(player.name.value, profit))
        }
    }

    companion object {
        private const val PRINT_GAME_INIT_MESSAGE = "\n딜러와 %s에게 ${RandomCardPicker.DRAW_INIT_CARD_COUNT}장의 나누었습니다."
        private const val SEPARATOR = ", "
        private const val PRINT_NAME_AND_CARDS = "%s카드: %s"
        private const val PRINT_DEALER_ADD_CARD = "\n%s는 ${Dealer.DEALER_ADD_CARD_CONDITION}이하라 한장의 카드를 더 받았습니다."
        private const val PRINT_PROFIT_INIT = "\n## 최종 수익"
        private const val PRINT_PROFIT = "%s: %f"
        private const val PRINT_NAME_AND_CARDS_AND_SCORE = "%s 카드: %s - 결과: %d"
    }
}
