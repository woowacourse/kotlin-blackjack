package blackjack.domain.player

import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardsGenerator

class BlackjackManager(cardsGenerator: CardsGenerator) {

    private val cardDeck = CardDeck(cardsGenerator)
    val dealer = Dealer()
    lateinit var participants: Participants
        private set

    fun setup(getNames: () -> List<String>) {
        participants = Participants(getNames().map { Participant(it) })
        repeat(CARD_SETTING_COUNT) { provideCard(dealer) }
        participants.values.forEach { participant ->
            repeat(CARD_SETTING_COUNT) {
                provideCard(participant)
            }
        }
    }

    fun provideParticipantMoreCard(
        participant: Participant,
        requestMoreCard: (String) -> Boolean,
        onProvideCard: (Participant) -> Unit
    ) {
        while (true) {
            val check = participant.checkProvideCardPossible()
            if (check) {
                val answer = requestMoreCard(participant.name)
                if (answer) provideCard(participant)
                onProvideCard(participant)
                if (!answer) break
            }
            if (!check) break
        }
    }

    fun provideParticipantsMoreCard(
        readMoreCard: (String) -> Boolean,
        onProvideCard: (Participant) -> Unit
    ) {
        participants.values.forEach {
            provideParticipantMoreCard(it, readMoreCard, onProvideCard)
        }
    }

    fun provideDealerMoreCard(onProvideCard: () -> Unit) {
        while (true) {
            if (!dealer.checkProvideCardPossible()) break
            provideCard(dealer)
            onProvideCard()
        }
    }

    fun calculatePlayersResult(onCalculateResults: (PlayersResults, DealerResult) -> Unit) {
        val playersResult = mutableListOf<PlayerResult>()
        participants.values.forEach {
            playersResult.add(it.calculateResult(dealer))
        }
        val dealerResult = dealer.calculateResults(participants)
        onCalculateResults(PlayersResults(playersResult), dealerResult)
    }

    private fun provideCard(player: Player) {
        cardDeck.apply {
            if (checkProvidePossible()) {
                player.addCard(provide())
            }
        }
    }

    companion object {
        private const val CARD_SETTING_COUNT = 2
    }
}
