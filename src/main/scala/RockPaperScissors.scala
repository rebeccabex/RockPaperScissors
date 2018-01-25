object RockPaperScissors {

  def main(args: Array[String]): Unit = {
    println(determineWinner("rock", "scissors"))
  }
  case class RPS(player1: String, player2: String)
  def determineWinner(p1Choice: String, p2Choice: String): Any = (rpsValue(p1Choice), rpsValue(p2Choice)) match {
    case (a, _) if a == -1 => -1
    case (_, b) if b == -1 => -2
    case (a, b) if a == b => 0
    case (a, b) => ((a - b) + 3) % 3
    case _ => println(s"p1: $p1Choice; p2: $p2Choice. " + rpsValue(p1Choice) + "-" + rpsValue(p2Choice))
  }


def rpsValue(choice: String): Int = choice.toLowerCase match {
  case "rock" => 1
  case "paper" => 2
  case "scissors" => 3
  case _ => -1
}

}
