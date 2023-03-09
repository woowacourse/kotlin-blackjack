package controller

import entity.CardNumber
import entity.CardType
import model.ManualCardFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import view.FakeInput
import view.FakeOutput
import view.GameView
import view.InitView
import view.ResultView

class BlackjackControllerTest {
    @Test
    fun `세 명의 플레이어를 가지고 게임을 1회 실행한다`() {
        // given
        val fakeConsole = StringBuilder()
        val input = FakeInput(
            listOf(
                "corgan,bix,sangoon",
                "1000",
                "2000",
                "3000",
                "y",
                "n",
                "n",
                "n"
            )
        )
        val output = FakeOutput(fakeConsole)
        val cardFactory = ManualCardFactory(
            listOf(
                CardType.HEART to CardNumber.FIVE,
                CardType.CLUB to CardNumber.ACE,
                CardType.SPADE to CardNumber.THREE,
                CardType.DIAMOND to CardNumber.NINE,
                CardType.DIAMOND to CardNumber.FIVE,
                CardType.CLUB to CardNumber.JACK,
                CardType.HEART to CardNumber.QUEEN,
                CardType.HEART to CardNumber.EIGHT,
                CardType.CLUB to CardNumber.EIGHT,
                CardType.SPADE to CardNumber.NINE,
            )
        )
        val blackjackController = BlackjackController(
            InitView(input, output), GameView(input, output), ResultView(output), cardFactory
        )

        // when
        blackjackController.process()

        // then
        val except = """게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
            |corgan의 배팅 금액은?
            |bix의 배팅 금액은?
            |sangoon의 배팅 금액은?
            |딜러와 corgan, bix, sangoon에게 2장의 나누었습니다.
            |딜러: 5❤️
            |corgan카드: 3♠️, 9♦️
            |bix카드: 5♦️, J♣️
            |sangoon카드: Q❤️, 8❤️
            |corgan는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
            |corgan카드: 3♠️, 9♦️, 8♣️
            |corgan는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
            |bix는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
            |sangoon는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
            |딜러는 16이하라 한장의 카드를 더 받았습니다.
            |딜러 카드: 5❤️, A♣️, 9♠️ - 결과: 15
            |corgan카드: 3♠️, 9♦️, 8♣️ - 결과: 20
            |bix카드: 5♦️, J♣️ - 결과: 15
            |sangoon카드: Q❤️, 8❤️ - 결과: 18
            |## 최종 승패
            |딜러: 2패 1무
            |corgan: 승
            |bix: 무
            |sangoon: 승
            |## 최종 수익
            |딜러: -8000
            |corgan: 2000
            |bix: 0
            |sangoon: 6000
            |""".trimMargin()
        assertThat(fakeConsole.toString()).isEqualTo(except)
    }
}
