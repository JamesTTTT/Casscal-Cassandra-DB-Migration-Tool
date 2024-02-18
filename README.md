
# Casscal - (Work in progress)
### A Cassandra database migration tool
This is a tool i have created for myself to easily simplify Casssandra database migration

The tool is entirely written in Scala 3.4

| Flag (Short) | Flag (Long)           | Description                                  | Input Type |
|--------------|-----------------------|----------------------------------------------|------------|
| `-u`         | `--username`          | Username for Cassandra authentication        | String     |
| `-p`         | `--password`          | Password for Cassandra authentication        | String     |
| `-k`         | `--keyspace`          | Keyspace to connect to in Cassandra          | String     |
| `-o`         | `--port`              | Port to connect to Cassandra                 | Int        |
| None         | `--contactPoints`     | Contact points for Cassandra, comma-separated| String     |
| None         | `--datacenter`        | Local datacenter for Cassandra connection    | String     |
| `-l`         | `--migrationFilePath` | Location for migrations folder               | String     |

### Usage Example

Run the `casscal` application with the following command, replacing each argument with your specific values:

```shell
casscal --username myUsername --password myPassword --keyspace myKeyspace --port 9042 --datacenter myDatacenter --migrationFilePath /path/to/migrations
```
```shell
casscal --contactPoints "127.0.0.1,192.168.1.2" -u username -p password -k keyspace -o 9042 -l "/path/to/migrations"
```
