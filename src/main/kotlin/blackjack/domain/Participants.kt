package blackjack.domain

class Participants(private val dealer: Dealer, private val players: Players) {
    fun drawAll(deck: CardDeck) {
        dealer.addCard(deck.draw())
        players.drawAll(deck)
    }

    // fun getInitialHands(): HandsDTO = HandsDTO(dealer.getFirstCardHand(), players.getHands())

    fun getPlayers(): List<Player> = players.toList()

    fun drawDealerCard(deck: CardDeck, block: (Boolean) -> Unit) {
        while (dealer.canDraw()) {
            dealer.addCard(deck.draw())
            block(true)
        }
        block(false)
    }

    fun getCards(): Map<String, List<Card>> = mapOf(dealer.name to dealer.getCards()) + players.getCards()

    //
    // fun getGameScores(): ScoresDTO {
    //     val dealerScore = dealer.getScore()
    //     val playersScore = players.toList().map(Player::getScore)
    //     return ScoresDTO(dealerScore, playersScore)
    // }

    fun judgePlayers(): PlayerResults = PlayerResults(
        players.toList().associate { player ->
            player.name to (dealer judge player.getTotalScore())
        }
    )
}
