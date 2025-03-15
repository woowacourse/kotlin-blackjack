package blackjack.model.participant

import blackjack.model.card.Card

class ParticipantManager {
    fun prepareParticipants(
        dealerName: Name,
        distributeCards: (Int) -> List<Card>,
        getPlayerNames: () -> List<Name>,
    ): Participants {
        val dealer = prepareDealer(dealerName, distributeCards)
        val players = preparePlayers(getPlayerNames(), distributeCards)

        return Participants(dealer, players)
    }

    private fun prepareDealer(
        dealerName: Name,
        distributeCards: (Int) -> List<Card>,
    ): Dealer {
        val dealer = Dealer.create(dealerName)
        dealer.receiveCards(distributeCards)

        return dealer
    }

    private fun preparePlayers(
        playerNames: List<Name>,
        distributeCards: (Int) -> List<Card>,
    ): Players {
        val players = Players.from(playerNames)
        players.value.forEach { player ->
            player.receiveCards(distributeCards)
        }

        return players
    }
}
