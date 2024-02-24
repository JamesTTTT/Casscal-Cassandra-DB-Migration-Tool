package casscal.version

import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.cql.PreparedStatement
import com.datastax.oss.driver.api.core.cql.{PreparedStatement, ResultSet, Row}
object VersionTracker {
  def createVersionTable(session: CqlSession): Unit = {
    val createTableStmt = """
      CREATE TABLE IF NOT EXISTS casscal.migration_version (
        version int PRIMARY KEY,
        applied_at timestamp
      );
    """
    session.execute(createTableStmt)
  }

  def insertVersion(session: CqlSession, version: Int): Unit = {
    val insertVersionStmt: PreparedStatement = session.prepare(
      "INSERT INTO casscal.migration_version (version, applied_at) VALUES (?, toTimestamp(now()));"    )
    session.execute(insertVersionStmt.bind(version))
  }

  def checkVersionApplied(session: CqlSession, version: Int): Boolean = {
    val checkVersionStmt: PreparedStatement = session.prepare(
      "SELECT version FROM casscal.migration_version WHERE version = ?;"
    )
    val resultSet: ResultSet = session.execute(checkVersionStmt.bind(version))
    val row: Row = resultSet.one()
    row != null
  }
}
