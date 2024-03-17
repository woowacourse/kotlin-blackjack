package blackjack.view

import blackjack.model.BlackjackGameStatistics
import blackjack.model.Card
import blackjack.model.CardNumber
import blackjack.model.Dealer
import blackjack.model.DealerStatistics
import blackjack.model.GameResult
import blackjack.model.Participant
import blackjack.model.Player
import blackjack.model.PlayerStatistics
import blackjack.model.RewardStatistic
import blackjack.model.RewardStatistics
import blackjack.model.Suit

object OutputView {
    fun printInitialResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println(buildParticipantCardsString(dealer.name, dealer.initialCardsList()))
        players.forEach {
            println(buildParticipantCardsString(it.name, it.initialCardsList()))
        }
    }

    fun printResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println()
        printParticipantStatusWithSum(dealer)
        players.forEach {
            printParticipantStatusWithSum(it)
        }
    }

    private fun printParticipantStatusWithSum(participant: Participant) {
        println(buildParticipantCards(participant) + " - 결과: " + participant.getCardSum())
    }

    fun printParticipantStatus(participant: Participant) {
        println(buildParticipantCards(participant))
    }

    private fun buildParticipantCards(participant: Participant): String {
        val name = participant.name
        val cards = participant.cardsList()
        return buildParticipantCardsString(name, cards)
    }

    private fun buildParticipantCardsString(
        name: String,
        cards: List<Card>,
    ): String {
        val cardStrings = cards.joinToString(", ") { it.cardNumber.getName() + it.suit.getName() }
        return "${name}카드: $cardStrings"
    }

    fun printBlackJackMessage(participant: Participant) {
        println("${participant.name}는 블랙잭 입니다.")
    }

    fun printBustedMessage(participant: Participant) {
        println("${participant.name}는 버스트 됐습니다.")
    }

    fun printDealerHitMessage() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printGameStatistics(blackjackGameStatistics: BlackjackGameStatistics) {
        printDealerStatistics(blackjackGameStatistics.dealerStatistics)
        printPlayerStatistics(blackjackGameStatistics.playerStatistics)
    }

    private fun printDealerStatistics(dealerStatistics: DealerStatistics) {
        val dealerStatisticsString = buildDealerStatisticsString(dealerStatistics)
        println("딜러: $dealerStatisticsString")
    }

    private fun buildDealerStatisticsString(dealerStatistics: DealerStatistics): String =
        buildString {
            val appendString: (Int, String) -> Unit = { count, resultName ->
                if (count != 0) {
                    append("${count}$resultName ")
                }
            }
            appendString(dealerStatistics.getWinCount(), GameResult.Win.getName())
            appendString(dealerStatistics.getLoseCount(), GameResult.Lose.getName())
            appendString(dealerStatistics.getDrawCount(), GameResult.Draw.getName())
        }

    private fun printPlayerStatistics(playerStatistics: PlayerStatistics) {
        playerStatistics.forEach { playerStatistic ->
            println("${playerStatistic.player.name}: ${playerStatistic.gameResult.getName()}")
        }
    }

    fun printRewardStatistics(rewardStatistics: RewardStatistics) {
        println("## 최종 수익")
        printParticipantRewardStatistic(rewardStatistics.dealerRewardStatistic)
        rewardStatistics.playerRewardStatistics.forEach {
            printParticipantRewardStatistic(it)
        }
    }

    private fun printParticipantRewardStatistic(rewardStatistic: RewardStatistic) {
        println("${rewardStatistic.participant.name}: ${rewardStatistic.reward.money}")
    }

    fun printError(message: String) = println(message)

    private fun Suit.getName(): String =
        when (this) {
            Suit.Club -> "클로버"
            Suit.Diamond -> "다이아몬드"
            Suit.Spade -> "스페이드"
            Suit.Heart -> "하트"
        }

    private fun CardNumber.getName(): String =
        when (this) {
            CardNumber.Ace -> "A"
            CardNumber.Two -> "2"
            CardNumber.Three -> "3"
            CardNumber.Four -> "4"
            CardNumber.Five -> "5"
            CardNumber.Six -> "6"
            CardNumber.Seven -> "7"
            CardNumber.Eight -> "8"
            CardNumber.Nine -> "9"
            CardNumber.Ten -> "10"
            CardNumber.Jack -> "J"
            CardNumber.Queen -> "Q"
            CardNumber.King -> "K"
        }

    private fun GameResult.getName(): String =
        when (this) {
            GameResult.Draw -> "무"
            GameResult.Win -> "승"
            GameResult.Lose -> "패"
        }
}
