package blackjack.view

import blackjack.model.BettingTable
import blackjack.model.Money
import blackjack.model.PlayerBehavior
import blackjack.model.card.CardShape
import blackjack.model.card.Denomination
import blackjack.model.participant.Dealer
import blackjack.model.participant.Participant
import blackjack.model.participant.Player
import blackjack.model.participant.Players

class BlackjackView {
    fun startGame(): Players {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val players =
            readln()
                .split(",")
                .map { Player(name = it.trim()) }
        return Players(players)
    }

    fun settingBettingAmount(players: Players): List<BettingTable> {
        val bettingTables = mutableListOf<BettingTable>()
        players.value.forEach { player ->
            println("\n${player.name}의 배팅 금액은?")
            val bettingAmount = Money(readln().trim().toInt())
            bettingTables.add(BettingTable(player, bettingAmount))
        }
        return bettingTables
    }

    fun giveCardsParticipants(
        dealer: Dealer,
        players: Players,
    ) {
        val playersName = players.value.map { it.name }.joinToString()
        println("\n${dealer.name}와 ${playersName}에게 2장의 나누었습니다.")
        showHands(dealer)
        players.value.forEach { player -> showHands(player) }
        println()
    }

    fun getPlayerBehavior(player: Player): PlayerBehavior {
        println("${player.name}는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)")
        val answer = readln().trim().uppercase()
        return when (answer) {
            "Y" -> PlayerBehavior.HIT
            "N" -> PlayerBehavior.STAY
            else -> throw IllegalArgumentException("예는 y, 아니오는 n로 응답해주세요")
        }
    }

    fun showHands(participant: Participant) {
        if (participant is Dealer && participant.state.hand.size == 2) {
            println("${participant.name}: ${showCards(participant)[0]}")
            return
        }
        println("${participant.name}: ${showCards(participant).joinToString()}")
    }

    fun showIsGetDealerCards() {
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun showResult(
        dealer: Dealer,
        players: Players,
    ) {
        println("\n${dealer.name}: ${showCards(dealer).joinToString()} - 결과: ${dealer.state.hand.score()}")
        players.value.forEach { player ->
            println("${player.name}: ${showCards(player).joinToString()} - 결과: ${player.state.hand.score()}")
        }
    }

    fun showBettingResult(
        dealer: Dealer,
        bettingTables: List<BettingTable>,
        profitRates: List<Float>,
    ) {
        println("\n## 최종 수익")
        var dealerProfitAmount = 0
        bettingTables.zip(profitRates).forEach { (bettingTable, profitRate) ->
            val profit = bettingTable.getProfit(profitRate).value
            dealerProfitAmount -= profit // 딜러는 상대방의 이익만큼 손해
        }
        println("${dealer.name}: $dealerProfitAmount")
        bettingTables.zip(profitRates).forEach { (bettingTable, profitRate) ->
            val participant = bettingTable.participant
            val profit = bettingTable.getProfit(profitRate).value
            println("${participant.name}: $profit")
        }
    }

    private fun showCards(participant: Participant): List<String> {
        val cards = participant.state.hand.cards
        return cards.map { card -> showDenomination(card.denomination) + showShapeName(card.shape) }
    }

    private fun showDenomination(denomination: Denomination): String =
        when (denomination) {
            Denomination.ACE -> "A"
            Denomination.TWO -> "2"
            Denomination.THREE -> "3"
            Denomination.FOUR -> "4"
            Denomination.FIVE -> "5"
            Denomination.SIX -> "6"
            Denomination.SEVEN -> "7"
            Denomination.EIGHT -> "8"
            Denomination.NINE -> "9"
            Denomination.TEN -> "10"
            Denomination.JACK -> "J"
            Denomination.QUEEN -> "Q"
            Denomination.KING -> "K"
        }

    private fun showShapeName(shape: CardShape): String =
        when (shape) {
            CardShape.DIAMOND -> "다이아몬드"
            CardShape.SPADE -> "스페이드"
            CardShape.HEART -> "하트"
            CardShape.CLOVER -> "클로버"
        }
}
