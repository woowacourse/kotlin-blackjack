package blackjack.domain.player

import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardsGenerator

class BlackjackManager(cardsGenerator: CardsGenerator) {

    val dealer = Dealer()
    lateinit var participants: Participants
        private set
    private val cardDeck = CardDeck(cardsGenerator)

    fun generateParticipants(getNames: () -> List<String>) {
        participants = Participants(getNames().map { Participant(it) })
    }

    fun settingPlayersCards(printResult: (Dealer, Participants) -> Unit) {
        repeat(CARD_SETTING_COUNT) {
            provideCard(dealer)
        }
        participants.values.forEach { participant ->
            repeat(CARD_SETTING_COUNT) {
                provideCard(participant)
            }
        }
        printResult(dealer, participants)
    }

    fun provideParticipantMoreCard(
        participant: Participant,
        requestMoreCard: (String) -> Boolean,
        printResult: (Participant) -> Unit
    ) {
        while (true) {
            val check = participant.checkProvideCardPossible()
            if (check) {
                val answer = requestMoreCard(participant.name)
                if (answer) provideCard(participant)
                printResult(participant)
                if (!answer) break
            }
            if (!check) break
        }
    }

    fun provideDealerMoreCard(printResult: () -> Unit) {
        while (true) {
            if (!dealer.checkProvideCardPossible()) break
            provideCard(dealer)
            printResult()
        }
    }

    fun updatePlayersResult() {
        participants.values.forEach {
            it.updateResult(dealer.cards.sumCardsNumber())
        }

        dealer.updateResults(participants.values.map { it.cards.sumCardsNumber() })
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
