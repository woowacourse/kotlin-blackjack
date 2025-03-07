package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.enums.Result

class OutputView {
    fun printDealingResult(
        dealer: Dealer,
        players: Players,
    ) {
        val playerNames = players.players.joinToString { it.name }
        println(MESSAGE_DEALING.format(playerNames))

        val dealerCard = dealer.hand.cards.first()
        println(MESSAGE_DEALER_CARD.format(cardInfo(dealerCard)))
        players.players.forEach { printPlayerCards(it) }
    }

    fun printPlayerCards(player: Player) {
        val playerCards = cardsInfo(player.hand.cards)
        println(MESSAGE_PLAYER_CARD.format(player.name, playerCards))
    }

    fun printBust(player: Player) {
        println(MESSAGE_BUST.format(player.name))
    }

    fun printDealerHit(hitCount: Int) {
        println(MESSAGE_DEALER_HIT.format(hitCount))
    }

    fun printBlackjackScore(
        dealer: Dealer,
        players: Players,
    ) {
        val dealerCards = cardsInfo(dealer.hand.cards)
        val dealerScore = dealer.calculateScore()
        println("${MESSAGE_DEALER_CARD.format(dealerCards)} ${MESSAGE_SCORE.format(dealerScore)}")

        players.players.forEach { player ->
            val playerCards = cardsInfo(player.hand.cards)
            val playerScore = player.calculateScore()
            println(
                "${MESSAGE_PLAYER_CARD.format(player.name, playerCards)} ${
                    MESSAGE_SCORE.format(
                        playerScore,
                    )
                }",
            )
        }
    }

    fun printMatchResult(
        dealerResult: Map<Result, Int>,
        playerResult: Map<Player, Result>,
    ) {
        println(MESSAGE_GAME_RESULT)
        val dealerWinCount = dealerResult.getValue(Result.WIN)
        val dealerDrawCount = dealerResult.getValue(Result.PUSH)
        val dealerLoseCount = dealerResult.getValue(Result.LOSE)
        println(MESSAGE_DEALER_RESULT.format(dealerWinCount, dealerDrawCount, dealerLoseCount))

        playerResult.forEach {
            println(MESSAGE_PLAYER_RESULT.format(it.key.name, it.value.message))
        }
    }

    private fun cardsInfo(cards: List<Card>): String = cards.joinToString { cardInfo(it) }

    private fun cardInfo(card: Card): String {
        val number = card.rank.symbol
        val shape = card.suit.korean
        return "$number$shape"
    }

    companion object {
        private const val MESSAGE_DEALING = "\n딜러와 %s에게 2장의 나누었습니다."
        private const val MESSAGE_BUST = "%s는 더 이상 카드를 받을 수 없습니다."
        private const val MESSAGE_DEALER_CARD = "딜러 카드: %s"
        private const val MESSAGE_PLAYER_CARD = "%s 카드: %s"
        private const val MESSAGE_SCORE = "- 결과: %d"
        private const val MESSAGE_DEALER_HIT = "\n딜러는 16이하라 %d장의 카드를 더 받았습니다.\n"
        private const val MESSAGE_GAME_RESULT = "\n## 최종 승패"
        private const val MESSAGE_DEALER_RESULT = "\n딜러: %d승 %d무 %d패"
        private const val MESSAGE_PLAYER_RESULT = "%s: %s"
    }
}
