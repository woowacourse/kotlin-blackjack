package blackjack.domain.blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.card.Cards
import blackjack.domain.participants.Participants
import blackjack.domain.participants.user.Dealer
import blackjack.domain.participants.user.Guest
import blackjack.domain.participants.user.Name
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BlackJackSettingTest {
    @Test
    fun `게임 결과를 반환한다`() {
        val blackJackSetting = BlackJackSetting(
            participants = Participants(Dealer(), listOf(Guest(name = Name("아크")), Guest(name = Name("로피")))),
            cardDeck = CardDeck(Cards.all()),
        )
        assertAll(
            { assertThat(blackJackSetting.participants.dealer.name.toString()).isEqualTo("딜러") },
            { assertThat(blackJackSetting.participants.guests[0].name.toString()).isEqualTo("아크") },
            { assertThat(blackJackSetting.participants.guests[1].name.toString()).isEqualTo("로피") },
        )
    }
}
