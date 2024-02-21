package casscal.api
import casscal.connector.CassandraConnector
import casscal.utils.util.{getMigrationFiles, applyMigration}
import casscal.cli.MigrationTool.applyMigration

object CasscalAPI {
  var session: CassandraConnector = None

  def connect(contactPoints: List[String],
              port: Int,
              keyspace: Option[String] = None,
              username: Option[String] = None,
              password: Option[String] = None,
              localDatacenter: String ):Unit = {
    session = CassandraConnector.createSession(contactPoints,port, keyspace, username, password, localDatacenter)
  }
  def migrate(filePath: String): Unit = {
    session match {
      case Some(sess) =>{
        try {
          getMigrationFiles(filePath).foreach(applyMigration(filePath, sess))
        } catch
          case e: Exception =>
            println(s"Failed to apply migration: $filePath. Error ${e.getMessage}")
      }
      case None =>
        println("No cassandra session established. Please connect first.")
      }
    }


}
