package casscal.parser
import casscal.config.Config
import scopt.OParser
object CommandLineParser {
  val builder: OParser.Builder[Config] = OParser.builder[Config]
  val parser: OParser[Unit, Config] = {
    import builder._
    OParser.sequence(
      programName("casscal"),
      opt[String]('u', "user")
        .action((x, c) => c.copy(user = Some(x)))
        .text("Username for Cassandra authentication"),
      opt[String]('p', "pass")
        .action((x, c) => c.copy(pass = Some(x)))
        .text("Password for Cassandra authentication"),
      opt[String]('k', "keyspace")
        .action((x, c) => c.copy(keyspace = Some(x)))
        .text("Keyspace to connect to in Cassandra"),
      opt[Int]('o', "port")
        .action((x, c) => c.copy(port = x))
        .text("Port to connect to Cassandra"),
      opt[String]('l', "migrations")
        .action((x, c) => c.copy(migrations = x))
        .text("Location for migrations folder")
    ,
    )
  }
  def parse(args: Array[String]): Option[Config] = {
    OParser.parse(parser, args, Config())
  }
}
