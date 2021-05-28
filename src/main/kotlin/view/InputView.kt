package view

object InputView {

    fun inputPlayerNames(): List<String> {
        return try{
            println("게임에 참여할 사람의 이름을 입력하세요")
            readLine()!!.split(",").map{it.trim()}
        }catch (e : Exception) {
            println("ERROR : "+ e.message)
            inputPlayerNames()
        }
    }

    fun inputBettingMoney(name: String): Int {
        return try{
            println("$name 의 베팅 금액은?")
            readLine()!!.toInt()
        } catch (e : Exception){
            println("ERROR : " + e.message)
            inputBettingMoney(name)
        }
    }
}