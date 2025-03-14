package blackjack.model

import blackjack.model.ResultCalculator.BLACKJACK_PROFIT_MULTIPLIER
import blackjack.model.ResultCalculator.TIE_PROFIT_MULTIPLIER
import blackjack.model.ResultCalculator.LOSS_PROFIT_MULTIPLIER
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

    fun calculateProfit(result: Map<Player, ResultType>): Map<Player, Double> {
        return result.map { (player, resultType) ->
            player to
                    when (resultType) {
                        ResultType.BLACKJACK -> player.betAmount * BLACKJACK_PROFIT_MULTIPLIER
                        ResultType.WIN -> player.betAmount.toDouble()
                        ResultType.TIE -> player.betAmount.toDouble() * TIE_PROFIT_MULTIPLIER
                        ResultType.LOSS -> player.betAmount * LOSS_PROFIT_MULTIPLIER
                    }
        }.toMap()
    }

    fun drawCard(person: Participant) {
        person.addCard(deck.draw())
    }

    companion object {
        const val INITIAL_HAND_OUT_CARD_COUNT = 2
    }
}
