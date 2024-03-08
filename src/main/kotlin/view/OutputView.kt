package view

import model.Hand
import model.ResultType
import model.human.Dealer
import model.human.Human
import model.human.HumanName
import model.human.Player
import model.human.Players

object OutputView {
    private const val HEADER_GAME_INITIAL_STATE = "\n딜러와 %s에게 2장의 카드를 나누었습니다."
    private const val HEADER_DRAW_CARDS_FOR_DEALER = "\n딜러는 16이하라 한장의 카드를 더 받았습니다."
    private const val HEADER_RESULT = "\n## 최종승패"
    private const val DEALER_HAND = "딜러: %s"
    private const val SPACE = " "

    fun showGameInit(
        dealer: Dealer,
        players: Players,
    ) {
        showInitHeader(players)
        showHands(dealer, players)
    }

    private fun showInitHeader(players: Players) {
        println(HEADER_GAME_INITIAL_STATE.format(players.players.joinToString(", ") { it.humanName.name }))
    }

    private fun showHands(
        dealer: Dealer,
        players: Players,
    ) {
        showDealerHand(dealer)
        showPlayerHands(players)
        println()
    }

    fun showDealerHand(dealer: Dealer) {
        println("딜러: ${getHand(dealer.hand)}")
    }

    fun showHandWithResult(human: Human) {
        println("${human.humanName.name}: ${getHand(human.hand)} - 결과: ${human.getPointIncludingAce().amount}")
    }

    fun showDealerHandWithResult(dealer: Dealer) {
        println()
        showHandWithResult(dealer)
    }

    fun showPlayersHandWithResult(players: Players) {
        players.players.forEach {
            showHandWithResult(it)
        }
    }

    fun showPlayerHands(players: Players) {
        players.players.forEach {
            showPlayerHand(it)
        }
    }

    fun showPlayerHand(player: Player) {
        println("${player.humanName.name}: ${getHand(player.hand)}")
    }

    fun getHand(hand: Hand): String = hand.cards.joinToString(", ") { it.valueType.rank + it.markType.mark }

    fun drawCardForDealer() = println(HEADER_DRAW_CARDS_FOR_DEALER)

    fun showResultHeader() = println(HEADER_RESULT)

    fun showDealerResult(dealerResultType: Map<ResultType, Int>) {
        println(
            DEALER_HAND.format(dealerResultType.map {
                it.value.toString() + it.key.word
            }.joinToString(SPACE))
        )
    }

    fun showPlayersResult(
        players: Players,
        playersResultType: Map<HumanName, ResultType>,
    ) {
        players.players.forEach { player ->
            println("${player.humanName.name}: ${playersResultType.getOrDefault(player.humanName, ResultType.DRAW).word}")
        }
    }
}
