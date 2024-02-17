package casscal.config

case class Config(username: Option[String] = None,
                  password: Option[String] = None,
                  keyspace: Option[String] = None,
                  port: Int = 9042,
                  contactPoints: Seq[String] = Seq("127.0.0.1"),
                  migrationFilePath: String = "./migrations")
