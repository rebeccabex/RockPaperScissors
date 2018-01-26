package rps

class SmartAI extends AI {

  // Based on result of last hand, what opponent last played, then counts how many times they played each hand in situation
  val zeroes = Array.fill[Int](3)(0)
  val subrecord = Array.fill[Array[Int]](3)(zeroes)
  val record = Array.fill[Array[Array[Int]]](3)(subrecord)
  var prevOppChoice = ""

  def addToRecord(newOppChoice: String, winner: Int): Unit = {
    if (!prevOppChoice.isEmpty)
      record(winner)(RPS.rpsValue(prevOppChoice))(RPS.rpsValue(newOppChoice)) += 1
    prevOppChoice = newOppChoice
  }

  def chooseFromRecord(): String = {
    if (!prevOppChoice.isEmpty) {
      val rock = record(lastHand)(RPS.rpsValue(prevOppChoice))(0)
      val paper = record(lastHand)(RPS.rpsValue(prevOppChoice))(1)
      val scissors = record(lastHand)(RPS.rpsValue(prevOppChoice))(2)

      (rock, paper, scissors) match {
        case (a, b, c) if (a > b) && (a > c) => "paper"
        case (a, b, c) if (b > a) && (b > c) => "scissors"
        case (a, b, c) if (c > a) && (c > b) => "rock"
        case (a, b, c) if (a > c) && (a == b) => chooseFromTwo("paper", "scissors")
        case (a, b, c) if (a > b) && (a == c) => chooseFromTwo("rock", "paper")
        case (a, b, c) if (b > a) && (b == c) => chooseFromTwo("scissors", "rock")
        case _ => chooseWithBias()
      }
    } else
      chooseWithBias()
  }

  override def choose(): String = {
    val newChoice = scala.util.Random.nextInt(3) match {
      case 0 => chooseWithBias()
      case 1 => chooseFromRecord()
      case 2 => choosePreferred()
    }
    lastChoice = newChoice
    newChoice
  }

  def chooseFromTwo(choices: (String, String)): String = {
    scala.util.Random.nextInt(3) match {
      case 0 | 2 => choices._1
      case 1 => choices._2
    }
  }

}
