package blackjack.view

import blackjack.model.card.CardNumber
import blackjack.model.card.Suit
import blackjack.model.playing.cardhand.CardHand
import blackjack.model.playing.participants.Participants
import blackjack.model.playing.participants.Role
import blackjack.model.winning.DealerWinning
import blackjack.model.winning.FinalWinning
import blackjack.model.winning.PlayersWinning
import blackjack.model.winning.WinningResultStatus

class OutputView {
    fun printInitialSetting(participants: Participants) {
        val dealer = participants.dealer

        val players = participants.players
        println(dealer.name.name + "와 " + players.players.joinToString { it.name.name } + "에게 2장씩 카드를 나눴습니다")
    }

    fun printInitialCardHands(participants: Participants) {
        print(NAME_CARD_HAND_FORMAT.format(participants.dealer.name.name))
        printFirstCardHand(participants.dealer.cardHand)

        participants.players.players.forEach {
            print(NAME_CARD_HAND_FORMAT.format(it.name.name))
            printAllCardHand(it.cardHand)
            println()
        }
    }

    private fun printFirstCardHand(cardHand: CardHand) {
        val (suit, number) = cardHand.hand.first()
        println(number.number.toString() + convertCardShapeFormat(suit))
    }

    fun printDealerHit() = println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.")

    fun printGameResult(participants: Participants) {
        println("\n")

        printPlayerCardHand(participants.dealer)
        println(CARD_HAND_SUM_FORMAT.format(participants.dealer.cardHand.calculateScore()))

        participants.players.players.forEach {
            printPlayerCardHand(it)
            println(CARD_HAND_SUM_FORMAT.format(it.cardHand.calculateScore()))
        }
    }

    private fun printAllCardHand(cardHand: CardHand) {
        print(
            cardHand.hand.joinToString {
                convertCardNumberFormat(it.number) + convertCardShapeFormat(it.suit)
            },
        )
    }

    private fun convertCardShapeFormat(suit: Suit): String {
        return when (suit) {
            Suit.HEART -> HEART_OUTPUT_FORMAT
            Suit.CLOVER -> CLOVER_OUTPUT_FORMAT
            Suit.SPADE -> SPADE_OUTPUT_FORMAT
            Suit.DIAMOND -> DIAMOND_OUTPUT_FORMAT
        }
    }

    private fun convertCardNumberFormat(number: CardNumber): String {
        return if (number == CardNumber.ACE) {
            ACE_OUTPUT_FORMAT
        } else {
            number.number.toString()
        }
    }

    fun printPlayerCardHand(role: Role) {
        print(NAME_CARD_HAND_FORMAT.format(role.name.name))
        printAllCardHand(role.cardHand)
    }

    fun printFinalWinning(finalWinning: FinalWinning) {
        printFinalDealerResult(finalWinning.dealerWinning)
        printFinalPlayersResult(finalWinning.playerWinning)
    }

    private fun printFinalDealerResult(dealerWinning: DealerWinning) {
        println("\n## 최종 승패")
        println(DEALER_WINNING_OUTPUT_FORMAT.format(dealerWinning.victory, dealerWinning.defeat, dealerWinning.push))
    }

    private fun printFinalPlayersResult(playersWinning: PlayersWinning) {
        playersWinning.result.forEach { (name, status) ->
            println("${name.name}: ${convertWinningResultFormat(status)}")
        }
        println()
    }

    private fun convertWinningResultFormat(status: WinningResultStatus): String {
        return when (status) {
            WinningResultStatus.VICTORY -> VICTORY_OUTPUT_FORMAT
            WinningResultStatus.DEFEAT -> DEFEAT_OUTPUT_FORMAT
            WinningResultStatus.PUSH -> PUSH_OUTPUT_FORMAT
        }
    }

    companion object {
        private const val NAME_CARD_HAND_FORMAT = "%s 카드: "
        private const val CARD_HAND_SUM_FORMAT = " - 결과: %d"
        private const val HEART_OUTPUT_FORMAT = "하트"
        private const val CLOVER_OUTPUT_FORMAT = "클로버"
        private const val SPADE_OUTPUT_FORMAT = "스페이드"
        private const val DIAMOND_OUTPUT_FORMAT = "다이아몬드"
        private const val ACE_OUTPUT_FORMAT = "A"
        private const val DEALER_WINNING_OUTPUT_FORMAT = "딜러: %d승 %d패 %d무"
        private const val VICTORY_OUTPUT_FORMAT = "승"
        private const val DEFEAT_OUTPUT_FORMAT = "패"
        private const val PUSH_OUTPUT_FORMAT = "무"
    }
}
