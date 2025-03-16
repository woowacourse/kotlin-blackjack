package blackjack.domain.participant

import blackjack.domain.BlackjackResult
import blackjack.domain.GameResult
import blackjack.domain.card.Card
import blackjack.domain.card.Deck

class Participants(
    val dealer: Dealer,
    val players: List<Player>,
) {
    init {
        require(players.size in MIN_PLAYER_COUNT..MAX_PLAYER_COUNT) { ERROR_INVALID_PLAYER_COUNT }
    }

    fun drawCard(deck: Deck) {
        dealer.receiveCard(deck.pick())
        players.forEach {
            it.receiveCard(deck.pick())
        }
    }

    fun playGame(
        draw: () -> Card,
        onPlayerChoice: (Participant) -> Boolean,
        onPlayerDraw: (Participant) -> Unit,
        onDealerDraw: (Participant) -> Unit,
    ) {
        playPlayersTurn(draw, onPlayerChoice, onPlayerDraw)
        playDealerTurn(draw, onDealerDraw)
    }

    fun blackjackResult(): BlackjackResult = BlackjackResult(dealerResult(), playersResult())

    private fun playPlayersTurn(
        draw: () -> Card,
        onChoice: (Participant) -> Boolean,
        onDraw: (Participant) -> Unit,
    ) {
        players.forEach { player ->
            player.playGame(
                draw = draw,
                shouldContinue = onChoice,
                onDraw = onDraw,
            )
        }
    }

    private fun playDealerTurn(
        draw: () -> Card,
        onDraw: (Participant) -> Unit,
    ) {
        dealer.playGame(
            draw = draw,
            shouldContinue = { true },
            onDraw = onDraw,
        )
    }

    private fun dealerResult(): Map<Player, GameResult> = players.associateWith { dealer.resultAgainst(it) }

    private fun playersResult(): Map<Player, GameResult> = players.associateWith { it.resultAgainst(dealer) }

    companion object {
        private const val MIN_PLAYER_COUNT = 1
        private const val MAX_PLAYER_COUNT = 7

        private const val ERROR_INVALID_PLAYER_COUNT = "1~7명의 플레이어가 참여할 수 있습니다."
    }
}
