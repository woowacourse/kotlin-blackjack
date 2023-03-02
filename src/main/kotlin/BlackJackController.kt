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
}
