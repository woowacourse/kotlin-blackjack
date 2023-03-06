//package blackjack.controller
//
//import blackjack.domain.BlackJackReferee
//import blackjack.domain.dealer.Dealer
//import blackjack.domain.player.Player
//import blackjack.view.InputView
//import blackjack.view.OutputView
//
//class BlackJackController(
//    private val dealer: Dealer = Dealer(),
//    private val blackJackReferee: BlackJackReferee = BlackJackReferee()
//) {
//
//    private val players: List<Player> by lazy {
//        InputView.requestPlayersName().map { name ->
//            Player(name)
//        }
//    }
//
//    fun run() {
//        runCatching {
//            showDividingCards()
//            drawAdditionalCards()
//            drawAdditionalCardForDealer()
//            showFinalCards()
//            judgeGameResults()
//        }.onFailure { exception ->
//            OutputView.printErrorMessage(exception)
//        }
//    }
//
//    private fun showDividingCards() = OutputView.printCardDividingMessage(dealer, players)
//
//    private fun drawAdditionalCards() = players.forEach { player ->
//        askToDrawAdditionalCardForPlayer(player)
//    }
//
//    private fun askToDrawAdditionalCardForPlayer(player: Player) {
//        do {
//            val drawFlag = InputView.requestAdditionalDraw(player)
//
//        } while (drawFlag && drawAdditionalCardForPlayer(player))
//
//        if (player.isDrawnNothing()) {
//            OutputView.printCardResults(player)
//        }
//    }
//
//    private fun drawAdditionalCardForPlayer(player: Player): Boolean {
//        val isPossibleToAdditionalDraw = player.drawCard()
//
//        OutputView.printCardResults(player)
//
//        return isPossibleToAdditionalDraw
//    }
//
//    private fun drawAdditionalCardForDealer() {
//        val drawResult = dealer.drawCard()
//
//        OutputView.printIsDealerReceivedCard(drawResult)
//    }
//
//    private fun showFinalCards() = OutputView.printFinalCards(dealer, players)
//
//    private fun judgeGameResults() {
//        val totalGameResult = blackJackReferee.judgeTotalGameResults(players, dealer)
//
//        OutputView.printGameResults(totalGameResult)
//    }
//}
