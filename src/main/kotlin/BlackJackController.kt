class BlackJackController(
    private val dealer: Dealer = Dealer()
) {

    lateinit var players: List<Player>

    private fun initGamePlayers() {
        players = InputView.requestPlayersName().map { name ->
            Player(name)
        }
    }
}
