package blackjack.domain.blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.card.Cards
import blackjack.domain.participants.Dealer
import blackjack.domain.participants.Guest
import blackjack.domain.participants.Participants
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BlackJackTest {
    @Test
    fun `게임 결과를 반환한다`() {
        val blackJack = BlackJack(
            participants = Participants(Dealer(), listOf(Guest("아크"), Guest("로피"))),
            cardDeck = CardDeck(Cards.all()),
        )
        assertAll(
            { assertThat(blackJack.participants.dealer.name.toString()).isEqualTo("딜러") },
            { assertThat(blackJack.participants.guests[0].name.toString()).isEqualTo("아크") },
            { assertThat(blackJack.participants.guests[1].name.toString()).isEqualTo("로피") },
        )
    }
}
