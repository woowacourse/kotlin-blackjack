package blackjack.view

import blackjack.model.Participant.Dealer
import blackjack.model.Participant.Player
import blackjack.model.Participants

object OutputView {
    private const val MESSAGE_CARD_DISTRIBUTION = "\n%s와 %s에게 2장의 카드를 나누었습니다."
    private const val MESSAGE_DEALER_CARD_INFORMATION = "%s: %s"
    private const val MESSAGE_PLAYER_CARD_INFORMATION = "%s 카드: %s"
    private const val COMMA = ", "

    fun outputCardDistribution(participants: Participants) {
        val dealerName = participants.dealer.name
        val playerNames = participants.players.map { player -> player.name }.joinToString(COMMA)
        println(MESSAGE_CARD_DISTRIBUTION.format(dealerName, playerNames))
        outputDealerCard(participants.dealer)
        outputPlayersCards(participants.players)
    }

    private fun outputDealerCard(dealer: Dealer) {
        println(MESSAGE_DEALER_CARD_INFORMATION.format(dealer.name, dealer.gameInformation.cards.elementAt(0)))
    }

    private fun outputPlayersCards(players: List<Player>) {
        players.forEach { player ->
            println(
                MESSAGE_PLAYER_CARD_INFORMATION.format(
                    player.name,
                    player.gameInformation.cards.joinToString(),
                ),
            )
        }
    }
}
