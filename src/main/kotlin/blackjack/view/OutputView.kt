package blackjack.view

import blackjack.model.Card
import blackjack.model.Participant
import blackjack.model.Participants
import blackjack.model.Player
import blackjack.model.Suit

class OutputView {
    fun printStartMessage() {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    }

    fun printBettingMessage(player: Player) {
        println("${player.name}의 배팅 금액은?")
    }

    fun printParticipantCards(participants: Participants) {
        val playersNames: String = participants.players.joinToString(", ") { it.name }
        println("\n${participants.dealer.name}와 ${playersNames}에게 2장의 카드를 나누었습니다.")
        println("${participants.dealer.name}: ${participants.dealer.openCard.joinToString { it.toBlackjackView() }}")
        participants.players.forEach { player ->
            printPlayerCard(player)
        }
        println()
    }

    fun printPlayerCard(player: Player) {
        println("${player.name}카드: ${player.openCard.joinToString { it.toBlackjackView() }}")
    }

    fun printDealerBlackjack() {
        println("딜러의 블랙잭으로 게임이 종료됩니다.")
    }

    fun printPlayerBehaviorGuide(player: Player) {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
    }

    fun printDealerGettingCard() {
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printResult(participants: Participants) {
        printParticipantResult(participants.dealer)
        participants.players.forEach { player ->
            printParticipantResult(player)
        }

        printTotalResult(participants)
    }

    private fun printParticipantResult(participant: Participant) {
        val participantCards: String = participant.hand.value.joinToString { it.toBlackjackView() }
        println("${participant.name}카드: $participantCards - 결과: ${participant.hand.getScore()}")
    }

    private fun printTotalResult(participants: Participants) {
        println("\n## 최종 수익")
        println("${participants.dealer.name}: ${participants.dealer.calculateProfits(participants.players).value}")
        participants.players.forEach { player ->
            println("${player.name}: ${player.calculateProfit(participants.dealer.hand).value}")
        }
    }

    private fun Card.toBlackjackView(): String = denomination.title + suit.toKoreanName()

    private fun Suit.toKoreanName(): String =
        when (this) {
            Suit.HEART -> "하트"
            Suit.SPADE -> "스페이드"
            Suit.DIAMOND -> "다이아몬드"
            Suit.CLOVER -> "클로버"
        }
}
