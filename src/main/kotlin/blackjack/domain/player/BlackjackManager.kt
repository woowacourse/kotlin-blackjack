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

    fun settingPlayersCards() {
        repeat(CARD_SETTING_COUNT) {
            provideCard(dealer)
        }
        participants.values.forEach { participant ->
            repeat(CARD_SETTING_COUNT) {
                provideCard(participant)
            }
        }
    }

    fun provideParticipantMoreCard(participant: Participant, readMoreCard: () -> Boolean) {
        val check = participant.checkProvideCardPossible()
        if (!check) return
        val answer = readMoreCard()
        if (!answer) return
        provideCard(participant)
    }

    fun provideDealerMoreCard() {
        while (true) {
            if (!dealer.checkProvideCardPossible()) break
            provideCard(dealer)
        }
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
