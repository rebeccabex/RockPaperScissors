package rps

class Human(number: Int) extends Player(number) {

  override def choose(): String = {
    readInput("Enter choice, or 'quit' to exit: ")
  }

  def readInput(prompt: String): String = {
    print(prompt)
    scala.io.StdIn.readLine()
  }


}
