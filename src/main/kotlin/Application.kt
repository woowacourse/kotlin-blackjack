import controller.BlackJackGameController
import view.InputView
import view.ResultView

fun main() {
    val t = Thread(BlackJackGameController(InputView(), ResultView()))
    t.setUncaughtExceptionHandler { _, e ->
        println(e.message)
        println("예상치 못한 오류가 발생해서 프로그램을 종료합니다.")
    }
    t.start()
}
