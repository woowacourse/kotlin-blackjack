class BlackJackController(
    private val dealer: Dealer = Dealer()
) {

    private lateinit var players: List<Player>

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
}
