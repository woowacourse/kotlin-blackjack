package view

import domain.card.Card
import domain.participants.Dealer
import domain.participants.Player

object OutputView {
    private const val SEPARATOR = ", "
    private const val WITH_DEALER = "딜러와 "
    private const val DIVIDE_TWO_CARDS = "에게 2장을 나누었습니다."

    private const val DEALER = "딜러: "
    private const val PARTICIPANT_CARD = "카드:"
    private const val RESULT = " - 결과: "
    private const val FINAL_RESULT = "\n## 최종 수익"
    private const val PICK_CARD_OVER_SIXTEEN = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n"

    fun printBlackjackSetting(players: List<Player>, dealer: Dealer) {
        printDivideCard(players)
        printDealerSettingCard(dealer)
        printPlayersSettingCards(players)
    }

    private fun printDivideCard(players: List<Player>) {
        println()
        val names = players.map { it.name }
        println(names.joinToString(SEPARATOR, WITH_DEALER, DIVIDE_TWO_CARDS))
    }

    private fun printDealerSettingCard(dealer: Dealer) {
        println(DEALER + printCardForm(dealer.ownCards.cards.first()))
    }

    private fun printPlayersSettingCards(players: List<Player>) {
        players.forEach {
            printParticipantCards(it)
        }
        println()
    }

    fun printParticipantCards(player: Player) {
        println("${player.name}$PARTICIPANT_CARD ${player.ownCards.cards.joinToString(SEPARATOR) { printCardForm(it) }}")
    }

    private fun printCardForm(card: Card): String {
        return card.cardValue.title + card.shape.shape
    }

    fun printDealerUnder16() {
        println(PICK_CARD_OVER_SIXTEEN)
    }

    fun printCardResult(dealer: Dealer, participants: List<Player>) {
        printDealerCardResult(dealer)
        participants.forEach {
            printPlayerCardResult(it)
        }
    }

    private fun printDealerCardResult(dealer: Dealer) {
        println(
            "${dealer.name} $PARTICIPANT_CARD " +
                dealer.ownCards.cards.joinToString { printCardForm(it) } +
                "${RESULT}${dealer.ownCards.calculateCardSum()}"
        )
    }

    private fun printPlayerCardResult(player: Player) {
        println(
            "${player.name}$PARTICIPANT_CARD " +
                player.ownCards.cards.joinToString { printCardForm(it) } +
                "${RESULT}${player.ownCards.calculateCardSum()}"
        )
    }

    fun printProfitResult(players: List<Player>) {
        println(FINAL_RESULT)
        printDealerProfitResult(players)
        printPlayerProfitResult(players)
    }

    private fun printDealerProfitResult(players: List<Player>) {
        val dealerResult = players.map {
            it.result.calculateProfit(it.bettingMoney) * -1
        }.sum()
        println("$DEALER$dealerResult")
    }

    private fun printPlayerProfitResult(players: List<Player>) {
        players.forEach {
            println("${it.name}: ${it.result.calculateProfit(it.bettingMoney)}")
        }
    }
}
