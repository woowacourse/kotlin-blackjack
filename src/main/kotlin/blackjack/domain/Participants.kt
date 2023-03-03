package blackjack.domain

import blackjack.dto.HandsDTO

class Participants(private val dealer: Dealer, private val players: Players) {
    fun drawAll(deck: CardDeck) {
        dealer.addCard(deck.draw())
        players.drawAll(deck)
    }

    fun getInitialHands(): HandsDTO = HandsDTO(dealer.getFirstCardHand(), players.getHands())

    fun getPlayers(): List<Player> = players.toList()

    fun drawDealerCard(deck: CardDeck, block: (Boolean) -> Unit) {
        while (!dealer.isStay()) {
            dealer.addCard(deck.draw())
            block(true)
        }
        block(false)
    }

    fun getGameResults(): Map<String, String> = players.toList().associate {
        it.name to GameResult.judgePlayer(dealer.getTotalScore(), it.getTotalScore()).name
    }
}
