class BlackJackController(
    private val dealer: Dealer = Dealer(),
    private val blackJackReferee: BlackJackReferee = BlackJackReferee()
) {

    private lateinit var players: List<Player>

    fun run() {
        initGamePlayers()
        showDividingCards()
        drawAdditionalCards()
        drawAdditionalCardForDealer()
        showFinalCards()
        judgeGameResults()
    }

    private fun initGamePlayers() {
        players = InputView.requestPlayersName().map { name ->
            Player(name)
        }
    }

    private fun showDividingCards() = OutputView.printCardDividingMessage(dealer, players)

    private fun drawAdditionalCards() {
        players.forEach { player ->
            askToDrawAdditionalCard(player)
            if (player.cards.cards.size == 2) {
                OutputView.printCardResults(player)
            }
        }
    }

    private fun askToDrawAdditionalCard(player: Player) {
        do {
            val input = InputView.requestAdditionalDraw(player)
            if (input == "n") {
                return
            }
            player.drawCard()
            OutputView.printCardResults(player)
        } while (player.isPossibleToDraw())
    }

    private fun drawAdditionalCardForDealer() {
        // TODO: isPossibleToDraw의 위치? sealed class?
        val isReceived = dealer.isPossibleToDraw()

        if (isReceived) {
            dealer.drawCard()
        }
        OutputView.printIsDealerReceivedCard(isReceived)
    }

    private fun showFinalCards() = OutputView.printFinalCards(dealer, players)

    private fun judgeGameResults() {
        val playersGameResult = blackJackReferee.judgeGameResult(players, dealer)
        val dealerGameResult = playersGameResult.map { playerGameResult ->
            !playerGameResult.gameResult
        }

        OutputView.printGameResults(playersGameResult, dealerGameResult)
    }
}
