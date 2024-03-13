package blackjack.view

import blackjack.model.card.Card
import blackjack.model.card.Denomination
import blackjack.model.card.Suit
import blackjack.model.game.Result
import blackjack.model.game.State
import blackjack.model.game.State.Finished
import blackjack.model.player.Dealer
import blackjack.model.player.PlayerEntry

const val DEALER_CARD_RESULT = "\n딜러: %s - 결과: %d"
const val PLAYER_CARD_RESULT = "%s카드: %s - 결과: %d"
const val FINAL_WIN_OR_LOSS = "\n## 최종 승패"
const val BUST_STRING = "(Bust)"
const val BLACKJACK_STRING = "(BlackJack)"
const val EMPTY_STRING = ""
const val WIN_STRING = "승"
const val LOSE_STRING = "패"
const val DRAW_STRING = "무"
const val PLAYER_GAME_RESULT = "%s: %s"
const val DEALER_GAME_RESULT = "딜러: %d승%s %d패"

object ResultView {
    fun showHandsScore(
        dealer: Dealer,
        playerEntry: PlayerEntry,
    ) {
        showDealerScore(dealer)
        showPlayersScore(playerEntry)
    }

    private fun showFinalWinOrLoss() {
        println(FINAL_WIN_OR_LOSS)
    }

    fun displayCard(card: Card): String {
        val suit = displaySuit(card.suit)
        val denomination = displayDenomination(card.denomination)
        return "$suit$denomination"
    }

    private fun displayDenomination(denomination: Denomination): String {
        return when (denomination) {
            Denomination.JACK -> "J"
            Denomination.QUEEN -> "Q"
            Denomination.KING -> "K"
            Denomination.ACE -> "A"
            else -> denomination.score.toString()
        }
    }

    private fun displaySuit(suit: Suit): String {
        return when (suit) {
            Suit.CLUBS -> "♣" // 이모티콘 또는 이미지 경로로 변경 가능
            Suit.DIAMONDS -> "♦"
            Suit.HEARTS -> "♥"
            Suit.SPADES -> "♠"
        }
    }

    private fun showDealerScore(dealer: Dealer) {
        val state = getStateString(dealer.state)

        println(
            DEALER_CARD_RESULT.format(
                dealer.hand.cards.joinToString { card -> displayCard(card) },
                dealer.hand.totalScore,
            ) + state,
        )
    }

    private fun showPlayersScore(playerEntry: PlayerEntry) {
        println()
        playerEntry.players.forEach { player ->
            val state = getStateString(player.state)

            println(
                PLAYER_CARD_RESULT.format(
                    player.name,
                    player.hand.cards.joinToString { card -> displayCard(card) },
                    player.hand.totalScore,
                ) + state,
            )
        }
    }

    private fun getStateString(state: State): String {
        return when (state) {
            Finished.Bust -> BUST_STRING
            Finished.BlackJack -> BLACKJACK_STRING
            else -> EMPTY_STRING
        }
    }

    fun showFinalWinOrLossResult(
        results: List<Result>,
        playerEntry: PlayerEntry,
    ) {
        val winCount = results.count { it == Result.DEALER_WIN }
        val defeatCount = results.count { it == Result.PLAYER_WIN }
        val drawCount = results.count { it == Result.DRAW }

        showFinalWinOrLoss()
        showDealerWinsOrLoses(winCount, drawCount, defeatCount)
        results.withIndex().forEach { (index, result) ->
            val winOrLose = winOrLoseReturn(result)
            playerWinOrLose(playerEntry, index, winOrLose)
        }
    }

    private fun playerWinOrLose(
        playerEntry: PlayerEntry,
        index: Int,
        winOrLose: String,
    ) {
        println(PLAYER_GAME_RESULT.format(playerEntry.players[index].name, winOrLose))
    }

    private fun winOrLoseReturn(result: Result): String {
        val winOrLose =
            when (result) {
                Result.PLAYER_WIN -> WIN_STRING
                Result.DEALER_WIN -> LOSE_STRING
                else -> DRAW_STRING
            }
        return winOrLose
    }

    private fun showDealerWinsOrLoses(
        winCount: Int,
        drawCount: Int,
        defeatCount: Int,
    ) {
        println(
            DEALER_GAME_RESULT.format(
                winCount,
                (" ${drawCount}무").takeIf { drawCount != 0 } ?: "",
                defeatCount,
            ),
        )
    }
}
