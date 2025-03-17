package blackjack.domain.participant

import blackjack.domain.ACE_HEART
import blackjack.domain.SEVEN_HEART
import blackjack.domain.SIX_HEART
import blackjack.domain.TEN_HEART
import blackjack.model.betting.BettingTable
import blackjack.model.card.CardCount
import blackjack.model.hand.Hand
import blackjack.model.participant.Dealer
import blackjack.model.participant.Money
import blackjack.model.participant.Name
import blackjack.model.participant.Participants
import blackjack.model.participant.Players
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class ParticipantsTest {
    private lateinit var participants: Participants

    @BeforeEach
    fun setup() {
        participants =
            Participants.create(
                dealerName = Name("딜러"),
                distributeCards = { listOf() },
                getPlayerNames = { listOf(Name("공백")) },
            )
    }

    @Test
    fun `플레이어 수가 0명 이하이면 예외가 발생한다`() {
        // given
        val dealer = Dealer.create(Name("딜러"))

        // when & then
        assertThrows<IllegalArgumentException> {
            Participants(dealer, Players.from(emptyList()))
        }
    }

    @Test
    fun `딜러 1명과 참가자 2명을 포함한 참가자 객체를 생성한다`() {
        // given
        val distributeCards = { _: CardCount -> listOf(TEN_HEART, SIX_HEART) }
        val getPlayerNames = { listOf(Name("공백"), Name("오이")) }

        // when
        participants = Participants.create(Name("딜러"), distributeCards, getPlayerNames)

        // then
        assertEquals("딜러", participants.dealer.name.value)
        assertEquals(2, participants.players.value.size)
        assertTrue(participants.players.value.any { it.name.value == "공백" })
        assertTrue(participants.players.value.any { it.name.value == "오이" })
    }

    @Test
    fun `베팅 금액이 0 이하이면 예외를 발생시킨다`() {
        // given
        val players = Players.from("시아", "공백")
        val getBettingMoney: (Name) -> Money = { Money.ZERO }

        // when & then
        assertThrows<IllegalArgumentException> {
            participants.betMoney(getBettingMoney)
        }
    }

    @Test
    fun `0원을 초과하고 플레이어가 가진 잔액보다 적은 돈을 베팅한다`() {
        // given
        val players = Players.from("시아", "공백")
        val getBettingMoney: (Name) -> Money = { Money(1000.0) }

        // when & then
        assertDoesNotThrow {
            participants.betMoney(getBettingMoney)
        }
    }

    @Test
    fun `승패 결과에 따라 올바르게 베팅 결과가 계산된다`() {
        // given
        val dealer = Dealer.create(hand = Hand(listOf(TEN_HEART, SIX_HEART)))
        val players = Players.from("공백", "비비", "메다", "제이")

        players.value[0].receiveCards { listOf(TEN_HEART, ACE_HEART) }
        players.value[1].receiveCards { listOf(TEN_HEART, SEVEN_HEART) }
        players.value[2].receiveCards { listOf(TEN_HEART, SIX_HEART) }
        players.value[3].receiveCards { listOf(TEN_HEART) }

        val participants = Participants(dealer, players)
        val bettingTable = BettingTable()

        val money1 = Money(1000.0)
        val money2 = Money(2000.0)
        val money3 = Money(3000.0)
        val money4 = Money(4000.0)

        bettingTable.add(Name("공백"), money1)
        bettingTable.add(Name("비비"), money2)
        bettingTable.add(Name("메다"), money3)
        bettingTable.add(Name("제이"), money4)

        // when
        val resultTable = participants.profitResult(bettingTable)
        println(resultTable.value.values)

        // then
        assertEquals(money1 * (1.5), resultTable.value[Name("공백")])
        assertEquals(money2 * (1.0), resultTable.value[Name("비비")])
        assertEquals(money3 * (0.0), resultTable.value[Name("메다")])
        assertEquals(money4 * (-1.0), resultTable.value[Name("제이")])
    }
}
