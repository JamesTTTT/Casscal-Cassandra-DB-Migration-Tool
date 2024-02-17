import com.datastax.oss.driver.api.core.CqlSession
object CassandraConnector {
  def createSession(): CqlSession = {
    CqlSession.builder().build()
  }
}
