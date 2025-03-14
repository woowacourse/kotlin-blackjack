package blackjack.model

import blackjack.model.card.Deck.Companion.INITIAL_HAND_OUT_CARD_COUNT
import blackjack.model.state.CardDrawDecision
import blackjack.model.user.Dealer
import blackjack.model.user.Player
import blackjack.view.BlackjackInput
import blackjack.view.BlackjackOutput

typealias Participants = Pair<Dealer, List<Player>>
typealias GameFactor = Triple<Dealer, List<Player>, GameManager>

class BlackjackGame(
    val inputView: BlackjackInput,
    val outputView: BlackjackOutput,
) {
    fun start() {
        val (dealer, players, gameManager) = gameReady()
        gameStart(dealer, players, gameManager)
        gameResult(dealer, players, gameManager)
    }

    private fun gameReady(): GameFactor {
        val (dealer, players) = initParticipants()
        val gameManager = GameManager(dealer, players)

        outputView.printInitialHandOutCardMessage(players)
        gameManager.distributeInitialCardWithCount(INITIAL_HAND_OUT_CARD_COUNT)
        outputView.printAllPlayerHands(dealer, players)

        return Triple(dealer, players, gameManager)
    }

    private fun initParticipants(): Participants {
        val dealer = Dealer()
        val players = inputView.readPlayerNames()
        return Pair(dealer, players)
    }

    private fun gameStart(
        dealer: Dealer,
        players: List<Player>,
        gameManager: GameManager,
    ) {
        if (!dealer.isBlackjack()) {
            playersDrawCards(players, gameManager)
            dealerDrawCards(dealer, gameManager)
        }
    }

    private fun playersDrawCards(
        players: List<Player>,
        gameManager: GameManager,
    ) {
        players.forEach { player -> playerDrawOrStay(player, gameManager) }
    }

    private fun playerDrawOrStay(
        player: Player,
        gameManager: GameManager,
    ) {
        while (true) {
            val decision: CardDrawDecision = inputView.readCardDrawChoice(player)
            if (gameManager.distributeCardWithChoice(decision, player)) {
                outputView.printPlayerHands(player)
                if (player.isBust()) break
                continue
            }
            outputView.printPlayerHands(player)
            break
        }
    }

    private fun dealerDrawCards(
        dealer: Dealer,
        gameManager: GameManager,
    ) {
        val isDraw = dealer.isAvailDrawCard()
        if (isDraw) gameManager.distributeCard(dealer)
        outputView.printDealerHandStatus(isDraw)
    }

    private fun gameResult(
        dealer: Dealer,
        players: List<Player>,
        gameManager: GameManager,
    ) {
        val playersGameResult = gameManager.getPlayersGameResult()
        val dealerGameResult = gameManager.getDealerGameResult()
        outputView.printFinalHandStatus(dealer, players)
        outputView.printFinalResult(playersGameResult, dealerGameResult)
    }
}
