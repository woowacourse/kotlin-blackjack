package domain

import domain.card.Deck
import domain.money.Money
import domain.person.Participants
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BlackJackBuilderTest {

    private val testMoneys = mutableListOf(10000, 10000)
    private fun testNameMakers() = listOf("ver, bix")

    private fun testBettingMoney(name: String) = testMoneys.removeFirst()

    private fun testNamePrinter(participant: Participants) {}

    @Test
    fun `딜러와 플레이어들은 이름을 갖는다`() {
        // given
        val participantsBuilder = BlackJackBuilder(Deck.create())

        // when
        val participants = participantsBuilder.getInitialPersons(::testNameMakers)

        // then

        assertAll(
            { assertThat(participants.dealer.name).isEqualTo("딜러") },
            { participants.players.forEach { player -> assertThat(player.name).isIn(testNameMakers()) } },
        )
    }

    @Test
    fun `딜러와 플레이어는 카드 두장을 갖고 시작한다`() {
        // given
        val participantsBuilder = BlackJackBuilder(Deck.create())

        // when
        val participants = participantsBuilder.getInitialPersons(::testNameMakers)

        participantsBuilder.handOutInitialCards(participants, ::testNamePrinter)

        // then
        assertAll(
            { assertThat(participants.dealer.cards.value).hasSize(2) },
            { participants.players.forEach { player -> assertThat(player.cards.value).hasSize(2) } },
        )
    }

    @Test
    fun `플레이어는 원하는 금액을 배팅한다`() {
        // given
        val participantsBuilder = BlackJackBuilder(Deck.create())

        // when
        val participants = participantsBuilder.getInitialPersons(::testNameMakers)

        val bettingMoneys = participantsBuilder.getPlayerBettingMoneys(participants, ::testBettingMoney)

        // then
        participants.players.forEach { player -> assertThat(bettingMoneys[player.name]).isEqualTo(Money(10000)) }
    }
}
