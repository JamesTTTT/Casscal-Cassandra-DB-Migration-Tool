-- Create a keyspace for the CMS
CREATE KEYSPACE IF NOT EXISTS cms WITH replication = {
    'class': 'SimpleStrategy',
    'replication_factor': '3'
};

