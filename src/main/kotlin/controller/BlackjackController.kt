package controller

import domain.BlackjackGame
import domain.card.Card
import domain.deck.Deck
import domain.gamer.Participant
import domain.gamer.Player
import domain.judge.PlayerResultInfo
import domain.money.Money
import view.InputView
import view.OutputView

class BlackjackController() {

    fun startGame() {
        val names = InputView.inputPlayerNames()
        val moneys = InputView.inputPlayerBets(names).map { Money(it) }
        val blackjackGame = BlackjackGame(Deck(Card.getAllCard()))
        blackjackGame.startGame(names)
        printBlackjackSetting(names, blackjackGame)
        requestPickCard(blackjackGame)
        dealerPickCard(blackjackGame)
        printCardResult(blackjackGame)
        printWinningResult(blackjackGame, moneys)
    }

    private fun printBlackjackSetting(names: List<String>, blackjackGame: BlackjackGame) {
        OutputView.printDivideCard(names)
        OutputView.printDealerSettingCard(blackjackGame.dealer.cards.getCards()[0])
        OutputView.printParticipantsCards(blackjackGame.players.getPlayers())
    }

    private fun printWinningResult(blackjackGame: BlackjackGame, moneys: List<Money>) {
        val playerResults = blackjackGame.getPlayersWinningResult()
        val dealerResult = blackjackGame.judgeDealerResult(playerResults)
        val playerResultInfos = mutableMapOf<String, PlayerResultInfo>().apply {
            playerResults.onEachIndexed { index, playerResult ->
                put(playerResult.key, PlayerResultInfo(playerResult.value, moneys[index]))
            }
        }.toMap()
        OutputView.printWinningResult(dealerResult, playerResults)
        printRevenue(blackjackGame, playerResultInfos)
    }

    private fun printRevenue(
        blackjackGame: BlackjackGame,
        playerResultInfos: Map<String, PlayerResultInfo>
    ) {
        val playerWinnings = blackjackGame.getPlayerRewards(playerResultInfos)
        val dealerWinning = blackjackGame.calculateDealerRewards(playerWinnings.map { it.value })
        OutputView.printRevenue(dealerWinning, playerWinnings)
    }

    private fun printCardResult(blackjackGame: BlackjackGame) {
        val cardResult = mutableMapOf<String, Participant>(DEALER to blackjackGame.dealer)
        blackjackGame.players.getPlayers().map {
            cardResult.put(it.name, it)
        }
        OutputView.printCardResult(cardResult)
    }

    private fun requestPickCard(blackjackGame: BlackjackGame) {
        blackjackGame.players.getPlayers().forEach {
            repeatPickCard(blackjackGame, it)
        }
    }

    private fun repeatPickCard(blackjackGame: BlackjackGame, player: Player) {
        while (!blackjackGame.checkBurst(player)) {
            val answer = validatePickAnswer(player.name)
            if (answer == YES_ANSWER) blackjackGame.pickPlayerCard(player)
            if (answer == NO_ANSWER) return
            OutputView.printParticipantCards(player.name, player.cards)
        }
    }

    private fun validatePickAnswer(name: String): String {
        val answer = InputView.inputRepeatGetCard(name)
        return answer ?: validatePickAnswer(name)
    }

    private fun dealerPickCard(blackjackGame: BlackjackGame) {
        while (blackjackGame.checkDealerAvailableForPick()) {
            OutputView.printDealerUnder16()
            blackjackGame.pickDealerCard()
        }
    }

    companion object {
        private const val DEALER = "딜러"
        const val YES_ANSWER = "y"
        const val NO_ANSWER = "n"
    }
}
