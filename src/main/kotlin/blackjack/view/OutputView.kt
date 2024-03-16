package blackjack.view

import blackjack.model.Card
import blackjack.model.Dealer
import blackjack.model.DealerResult
import blackjack.model.Player
import blackjack.model.Rank
import blackjack.model.ScoreBoard
import blackjack.model.Suit
import blackjack.model.WinningState

class OutputView {
    fun showDivided(
        dealerFirstCard: Card,
        players: List<Player>,
    ) {
        println()
        println(MESSAGE_DIVIDED_CARDS.format(players.joinToString { it.name }))
        println(MESSAGE_PLAYER_HAND_CARDS.format(DEALER_NAME, dealerFirstCard.format()))
        players.forEach(::showPlayerHandCards)
    }

    fun showPlayerHandCards(player: Player) {
        val cards: List<Card> = player.hand.cards
        println(MESSAGE_PLAYER_HAND_CARDS.format(player.name, cards.format()))
    }

    fun showDealerHitCard() {
        println(MESSAGE_DEALER_HIT)
    }

    fun showDealerScore(
        cards: List<Card>,
        score: Int,
    ) {
        println()
        showPlayerScore(DEALER_NAME, cards, score)
    }

    fun showPlayerScore(
        name: String,
        cards: List<Card>,
        score: Int,
    ) {
        println(
            MESSAGE_PARTICIPANT_RESULT.format(
                name,
                cards.format(),
                score,
            ),
        )
    }

    fun showScoreBoard(scoreBoard: ScoreBoard) {
        println(MESSAGE_FINAL_RESULT)
        val (playersResult, dealerResult) = scoreBoard
        println(dealerResult.format())
        playersResult.forEach { playerResult ->
            println("${playerResult.player.name}: ${makeWinningStateLabel(playerResult.winningState)}")
        }
    }

    fun showProfits(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println(MESSAGE_FINAL_PROFIT)
        println(MESSAGE_DEALER_PROFIT.format(dealer.profit.amount))
        players.forEach { player ->
            println(MESSAGE_PLAYER_PROFIT.format(player.name, player.profit.amount))
        }
    }

    private fun DealerResult.format() =
        buildString {
            append("${DEALER_NAME}: ")
            WinningState.entries.forEach {
                append(map[it] ?: 0)
                append(makeWinningStateLabel(it))
                append(" ")
            }
        }

    private fun Card.format(): String {
        return "${makeRankLabel(rank)}${makeSuitLabel(suit)} "
    }

    private fun List<Card>.format(): String {
        return buildString {
            this@format.forEach {
                append(it.format())
            }
        }
    }

    private fun makeRankLabel(rank: Rank) =
        when (rank) {
            Rank.ACE -> "A"
            Rank.TWO -> "2"
            Rank.THREE -> "3"
            Rank.FOUR -> "4"
            Rank.FIVE -> "5"
            Rank.SIX -> "6"
            Rank.SEVEN -> "7"
            Rank.EIGHT -> "8"
            Rank.NINE -> "9"
            Rank.TEN -> "10"
            Rank.JACK -> "J"
            Rank.QUEEN -> "Q"
            Rank.KING -> "K"
        }

    private fun makeSuitLabel(suit: Suit) =
        when (suit) {
            Suit.CLUB -> "클로버"
            Suit.DIAMOND -> "다이아몬드"
            Suit.SPADE -> "스페이드"
            Suit.HEART -> "하트"
        }

    private fun makeWinningStateLabel(winningState: WinningState) =
        when (winningState) {
            WinningState.WIN -> "승"
            WinningState.LOSS -> "패"
            WinningState.DRAW -> "무"
        }

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val MESSAGE_PLAYER_HAND_CARDS = "%s 카드: %s"
        private const val MESSAGE_PARTICIPANT_RESULT = "%s 카드: %s- 결과 : %d"
        private const val MESSAGE_DIVIDED_CARDS = "딜러와 %s에게 2장의 카드를 나누었습니다."
        private const val MESSAGE_DEALER_HIT = "\n딜러는 16이하라 한장의 카드를 더 받았습니다."
        private const val MESSAGE_FINAL_RESULT = "\n## 최종 승패"
        private const val MESSAGE_FINAL_PROFIT = "\n## 최종수익"
        private const val MESSAGE_DEALER_PROFIT = "딜러 : %d"
        private const val MESSAGE_PLAYER_PROFIT = "%s : %d"
    }
}
