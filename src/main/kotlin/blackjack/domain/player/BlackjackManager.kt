package blackjack.domain.player

import blackjack.domain.card.CardDeck

class BlackjackManager {

    private val cardDeck = CardDeck()

    fun settingPlayersCards(dealer: Dealer, participants: Participants) {
        repeat(CARD_SETTING_COUNT) {
            provideCard(dealer)
        }
        participants.values.forEach { participant ->
            repeat(CARD_SETTING_COUNT) {
                provideCard(participant)
            }
        }
    }

    fun provideCard(player: Player) {
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
