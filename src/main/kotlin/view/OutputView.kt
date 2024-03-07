package view

import model.Dealer
import model.Hand
import model.Human
import model.Name
import model.Player
import model.Players
import model.Result

object OutputView {
    private const val HEADER_GAME_INITIAL_STATE = "\n딜러와 %s에게 2장의 카드를 나누었습니다."
    private const val HEADER_DRAW_CARDS_FOR_DEALER = "\n딜러는 16이하라 한장의 카드를 더 받았습니다."
    private const val HEADER_RESULT = "\n## 최종승패"

    fun showGameInit(
        dealer: Dealer,
        players: Players,
    ) {
        showInitHeader(players)
        showHands(dealer, players)
    }

    private fun showInitHeader(players: Players) {
        println(HEADER_GAME_INITIAL_STATE.format(players.players.joinToString(", ") { it.name.name }))
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
        println("${human.name.name}: ${getHand(human.hand)} - 결과: ${human.getPointIncludingAce().amount}")
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
        println("${player.name.name}: ${getHand(player.hand)}")
    }

    fun getHand(hand: Hand): String = hand.cards.joinToString(", ") { it.value.rank + it.mark.mark }

    fun drawCardForDealer() = println(HEADER_DRAW_CARDS_FOR_DEALER)

    fun showResultHeader() = println(HEADER_RESULT)

    fun showDealerResult(dealerResult: Map<Result, Int>) {
        println(
            "딜러: ${dealerResult.map {
                it.value.toString() + it.key.word
            }.joinToString(" ")}",
        )
    }

    fun showPlayersResult(
        players: Players,
        playersResult: Map<Name, Result>,
    ) {
        players.players.forEach { player ->
            println("${player.name.name}: ${playersResult.getOrDefault(player.name, Result.DRAW).word}")
        }
    }
}
