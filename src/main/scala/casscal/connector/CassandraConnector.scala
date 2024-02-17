import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.internal.core.ContactPoints
import sun.security.util.Password

import java.net.InetSocketAddress
object CassandraConnector {
  def createSession(contactPoints: List[String],
                    port: Int,
                    keyspace: Option[String] = None,
                    username: Option[String] = None,
                    password: Option[String] = None): CqlSession = {

    val builder = CqlSession.builder().build()
      .addContactPoints(contactPoints.map(InetSocketAddress.createUnresolved(-, port)))

    keyspace.foreach(builder.withKeyspace)

    (username, password) match{
      case (Some(u), Some(p)) => builder.withAuthCredentials(u, p)
      case _ =>
    }


    builder.build()
  }
}
