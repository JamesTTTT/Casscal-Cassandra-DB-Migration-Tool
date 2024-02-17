case class Config(user: Option[String] = None,
                  pass: Option[String] = None,
                  keyspace: Option[String] = None,
                  port: Int = 9042,
                  contactPoints: Seq[String] = Seq("127.0.0.1"))
