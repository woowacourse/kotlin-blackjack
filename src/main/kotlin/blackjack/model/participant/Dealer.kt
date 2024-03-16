package blackjack.model.participant

import blackjack.constants.GameResult
import blackjack.model.Amount
import blackjack.model.Hand
import blackjack.model.card.Card
import blackjack.model.card.CardProvider

class Dealer(hand: Hand = Hand()) : Role(hand) {
    constructor(cards: List<Card>) : this(Hand(cards.toMutableList()))

    override fun receivableMoreCard() = score() < MIN_CARD_SUM

    fun takeTurn(
        cardProvider: CardProvider,
        endRoundAction: () -> Unit,
    ) {
        while (receivableMoreCard()) {
            receiveCard(Card.provideCards(cardProvider))
            endRoundAction()
        }
    }

    fun profit(players: Players): Amount {
        return players.playerGroup.fold(Amount(0)) { acc, player ->
            acc + profitByPlayer(player)
        }
    }

    private fun profitByPlayer(player: Player): Amount {
        return player.battingAmount * gameResult(player).profitRate
    }

    private fun gameResult(player: Player): GameResult {
        return when {
            player.isBlackjack() -> GameResult.BLACKJACK_LOSE
            player.isBurst() -> GameResult.WIN
            isBurst() -> GameResult.LOSE
            else -> super.gameResult(player)
        }
    }

    companion object {
        private const val MIN_CARD_SUM = 17
    }
}
