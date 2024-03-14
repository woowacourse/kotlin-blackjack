package blackjack.view

import blackjack.model.Participant
import blackjack.model.Participant.Dealer
import blackjack.model.Participant.Player

object OutputView {
    private const val MESSAGE_CARD_DISTRIBUTION = "\n%s와 %s에게 2장의 카드를 나누었습니다."
    private const val MESSAGE_DEALER_CARD_INFORMATION = "%s: %s"
    private const val MESSAGE_PARTICIPANT_CARD_INFORMATION = "%s 카드: %s"
    private const val MESSAGE_DEALER_DRAW = "%s는 16이하라 한장의 카드를 더 받았습니다.\n"
    private const val COMMA = ", "

    fun outputCardDistribution(
        dealer: Dealer,
        players: List<Player>,
    ) {
        val dealerName = dealer.name
        val playerNames = players.map { player -> player.name }.joinToString(COMMA)
        println(MESSAGE_CARD_DISTRIBUTION.format(dealerName, playerNames))
        outputInitialDealerCard(dealer)
        outputPlayersCards(players)
    }

    fun outputParticipantCard(participant: Participant) {
        println(
            MESSAGE_PARTICIPANT_CARD_INFORMATION.format(
                participant.name,
                participant.gameInformation.cards.joinToString(separator = ", ") { card ->
                    card.convertCard()
                },
            ),
        )
    }

    fun outputDealerDraw(dealer: Dealer) {
        println(MESSAGE_DEALER_DRAW.format(dealer.name))
    }

    private fun outputInitialDealerCard(dealer: Dealer) {
        println(
            MESSAGE_DEALER_CARD_INFORMATION.format(
                dealer.name,
                dealer.gameInformation.cards.elementAt(0),
            ),
        )
    }

    private fun outputPlayersCards(players: List<Player>) {
        players.forEach { player ->
            outputParticipantCard(player)
        }
    }
}
