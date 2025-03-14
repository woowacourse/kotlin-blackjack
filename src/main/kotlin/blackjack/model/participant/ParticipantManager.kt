package blackjack.model.participant

import blackjack.model.card.CardDeck

class ParticipantManager {
    fun prepareParticipants(
        dealerName: String,
        cardDeck: CardDeck,
        getPlayerNames: () -> List<String>,
    ): Participants {
        val dealer = prepareDealer(dealerName, cardDeck)
        val players = preparePlayers(getPlayerNames(), cardDeck)

        return Participants(dealer, players)
    }

    private fun prepareDealer(
        dealerName: String,
        cardDeck: CardDeck,
    ): Dealer {
        val dealer = Dealer.create(dealerName)
        dealer.recieveCards(cardDeck::draw)

        return dealer
    }

    private fun preparePlayers(
        playerNames: List<String>,
        cardDeck: CardDeck,
    ): Players {
        val players = Players.from(playerNames)
        players.value.forEach { player ->
            player.recieveCards(cardDeck::draw)
        }

        return players
    }
}
