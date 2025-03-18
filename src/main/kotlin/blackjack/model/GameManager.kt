package blackjack.model

import blackjack.model.amount.WinningMoney
import blackjack.model.participant.Dealer
import blackjack.model.participant.Participant
import blackjack.model.participant.Player

class GameManager(
    private val dealer: Dealer,
    private val players: List<Player>,
) {
    private var deck = Deck()

    fun dealInitialCards() {
        deck = Deck()
        dealInitialCardWithCount()
    }

    fun dealInitialCardWithCount() {
        repeat(INITIAL_HAND_OUT_CARD_COUNT) {
            dealer.addCard(deck.draw())
            players.forEach { player -> player.addCard(deck.draw()) }
        }
    }

    fun calculateResultMap(): Map<Player, ResultType> {
        val playersStatus =
            players.associateBy(
                { player -> player },
                { player -> ResultCalculator.judgeScore(dealer, player) },
            )
        return playersStatus
    }

    fun gameResult(result: Map<Player, ResultType>): List<Profit> {
        val playerProfits =
            result.map { (player, resultType) ->
                Profit(player, calculateProfit(resultType, player))
            }

        val dealerProfit = calculateDealerProfit(playerProfits)
        return listOf(dealerProfit) + playerProfits
    }

    fun drawCard(person: Participant) {
        person.addCard(deck.draw())
    }

    fun calculateProfit(
        resultType: ResultType,
        player: Player,
    ) = WinningMoney(player.betAmount.value * resultType.profit)

    fun calculateDealerProfit(profitResults: List<Profit>): Profit {
        val totalPlayerProfit = profitResults.sumOf { it.winningMoney.amount }
        return Profit(dealer, WinningMoney(totalPlayerProfit * DEALER_PROFIT_MULTIPLIER))
    }

    companion object {
        const val INITIAL_HAND_OUT_CARD_COUNT = 2
        const val DEALER_PROFIT_MULTIPLIER = -1.0
    }
}
