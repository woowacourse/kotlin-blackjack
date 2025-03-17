package blackjack.domain

import blackjack.view.InputView
import blackjack.view.OutputView
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertTrue

class BlackJackGameTest {
    private lateinit var outputStream: ByteArrayOutputStream
    private lateinit var printStream: PrintStream

    @BeforeEach
    fun setUp() {
        outputStream = ByteArrayOutputStream()
        printStream = PrintStream(outputStream)
        System.setOut(printStream)
    }

    private fun setInput(input: String) {
        System.setIn(ByteArrayInputStream(input.toByteArray()))
    }

    @Test
    fun `블랙잭 게임을 정상적으로 수행한다`() {
        setInput("player1, player2\n100\n100\nn\nn\n")

        BlackJackGame(InputView(), OutputView()).play()

        val output = outputStream.toString()

        assertAll(
            { assertTrue(output.contains("게임에 참여할 사람의 이름을 입력하세요")) },
            { assertTrue(output.contains("배팅 금액은?")) },
            { assertTrue(output.contains("한장의 카드를 더 받겠습니까?")) },
            { assertTrue(output.contains("## 최종 수익")) },
        )
    }

    @Test
    fun `블랙잭 게임을 정상적으로 수행한다2`() {
        setInput("player1, player2\n100\n100\ny\nn\nn\n")

        BlackJackGame(InputView(), OutputView()).play()

        val output = outputStream.toString()

        assertAll(
            { assertTrue(output.contains("게임에 참여할 사람의 이름을 입력하세요")) },
            { assertTrue(output.contains("배팅 금액은?")) },
            { assertTrue(output.contains("한장의 카드를 더 받겠습니까?")) },
            { assertTrue(output.contains("## 최종 수익")) },
        )
    }

    @Test
    fun `잘못된 입력이 들어왔을 때 예외 메시지를 출력한다`() {
        setInput("player1, player2\n-100\n100\n100\nn\nn\n")

        BlackJackGame(InputView(), OutputView()).play()

        val output = outputStream.toString()
        output.contains("[ERROR]") shouldBe true
    }
}
