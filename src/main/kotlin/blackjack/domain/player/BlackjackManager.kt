package blackjack.domain.player

import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardsGenerator
import blackjack.domain.card.RandomCardsGenerator

class BlackjackManager(
    cardsGenerator: CardsGenerator = RandomCardsGenerator(),
    playerNames: List<String>
) {

    private val cardDeck = CardDeck(cardsGenerator)
    val dealer = Dealer()
    val participants: Participants

    init {
        participants = Participants(
            playerNames.map {
                Participant(it)
            }
        )
    }

    fun setup() {
        repeat(CARD_SETTING_COUNT) { provideCard(dealer) }
        participants.values.forEach { participant ->
            repeat(CARD_SETTING_COUNT) {
                provideCard(participant)
            }
        }
    }

    fun playParticipantsTurns(
        requestMoreCard: (String) -> Boolean,
        onProvideCard: (Participant) -> Unit
    ) {
        participants.values.forEach {
            playParticipantTurns(it, requestMoreCard, onProvideCard)
        }
    }

    private fun playParticipantTurns(
        participant: Participant,
        requestMoreCard: (String) -> Boolean,
        onProvideCard: (Participant) -> Unit
    ) {
        while (true) {
            val check = participant.checkProvideCardPossible()
            if (!check) break
            val answer = requestMoreCard(participant.name)
            if (answer) provideCard(participant)
            onProvideCard(participant)
            if (!answer) break
        }
    }

    fun playDealerTurns(onProvideCard: () -> Unit) {
        while (true) {
            if (!dealer.checkProvideCardPossible()) break
            provideCard(dealer)
            onProvideCard()
        }
    }

    fun calculatePlayersResult(onCalculateResults: (ParticipantsResults, DealerResult) -> Unit) {
        val participantsResults = participants.values.map { participant ->
            participant.calculateResult(dealer)
        }
        val dealerResult = dealer.calculateResults(participants)
        onCalculateResults(ParticipantsResults(participantsResults), dealerResult)
    }

    private fun provideCard(player: Player) {
        cardDeck.apply {
            val card = provide() ?: return
            player.addCard(card)
        }
    }

    companion object {
        private const val CARD_SETTING_COUNT = 2
    }
}
