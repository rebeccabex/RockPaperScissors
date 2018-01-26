package rps

object RPS {

  def rpsValue(choice: String): Int = choice.toLowerCase match {
    case "rock" => 1
    case "paper" => 2
    case "scissors" => 0
    case _ => -1
  }

  def rpsChoiceFromVal(choiceValue: Int): String = choiceValue match {
    case x if x % 3 == 1 => "rock"
    case x if x % 3 == 2 => "paper"
    case x if x % 3 == 0 => "scissors"
    case _ => "error"
  }

  def beats(choice: String): String = {
    rpsChoiceFromVal((rpsValue(choice) + 2) % 3)
  }

  def losesTo(choice: String): String = {
    rpsChoiceFromVal((rpsValue(choice) + 1) % 3)
  }

}
