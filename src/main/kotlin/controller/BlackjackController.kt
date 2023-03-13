package controller

import domain.BlackjackGame
import domain.card.Card
import domain.deck.Deck
import domain.gamer.Participant
import domain.gamer.Player
import domain.gamer.Players
import domain.gamer.cards.Cards
import domain.judge.PlayerResultInfo
import domain.money.Money
import view.InputView
import view.OutputView

class BlackjackController() {

    fun startGame() {
        val names = InputView.inputPlayerNames()
        val moneys = InputView.inputPlayerBets(names).map { Money(it) }
        val blackjackGame = BlackjackGame(Deck(Card.getAllCard()))
        val players = Players(names.map { Player(it, Cards(listOf())) })
        blackjackGame.startGame(players)
        printBlackjackSetting(blackjackGame, players)
        requestPickCard(blackjackGame, players)
        dealerPickCard(blackjackGame)
        printCardResult(blackjackGame, players)
        printWinningResult(blackjackGame, players, moneys)
    }

    private fun printBlackjackSetting(blackjackGame: BlackjackGame, players: Players) {
        OutputView.printDivideCard(players.players.map { it.name })
        OutputView.printDealerSettingCard(blackjackGame.dealer.cards.getCards()[0])
        OutputView.printParticipantsCards(players.players)
    }

    private fun printWinningResult(blackjackGame: BlackjackGame, players: Players, moneys: List<Money>) {
        val playerResults = blackjackGame.getPlayersWinningResult(players)
        val dealerResult = blackjackGame.judgeDealerResult(playerResults)
        val playerResultInfos = mutableMapOf<Player, PlayerResultInfo>().apply {
            playerResults.onEachIndexed { index, playerResult ->
                put(playerResult.key, PlayerResultInfo(playerResult.value, moneys[index]))
            }
        }.toMap()
        OutputView.printWinningResult(dealerResult, playerResults)
        printRevenue(blackjackGame, players, playerResultInfos)
    }

    private fun printRevenue(
        blackjackGame: BlackjackGame,
        players: Players,
        playerResultInfos: Map<Player, PlayerResultInfo>

    ) {
        val playerWinnings = blackjackGame.getPlayerRewards(players, playerResultInfos)
        val dealerWinning = blackjackGame.calculateDealerRewards(playerWinnings.map { it.value })
        OutputView.printRevenue(dealerWinning, playerWinnings)
    }

    private fun printCardResult(blackjackGame: BlackjackGame, players: Players) {
        val cardResult = mutableMapOf<String, Participant>(DEALER to blackjackGame.dealer)
        players.players.map {
            cardResult.put(it.name, it)
        }
        OutputView.printCardResult(cardResult)
    }

    private fun requestPickCard(blackjackGame: BlackjackGame, players: Players) {
        players.players.forEach {
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
