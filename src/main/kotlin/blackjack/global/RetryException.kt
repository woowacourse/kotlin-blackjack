package blackjack.global

import java.lang.IllegalStateException

class RetryException(e: String? = "여러번 잘못된 값을 입력하였습니다 다시 시도해주세요") : IllegalStateException(e)
