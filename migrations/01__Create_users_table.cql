-- V1__Create_users_table.cql
CREATE TABLE IF NOT EXISTS cms.users (
    username TEXT PRIMARY KEY,
    email TEXT,
    hashed_password TEXT,
    is_verified BOOLEAN
);

-- Table for storing content schemas
CREATE TABLE IF NOT EXISTS cms.content_schemas (
    schema_id UUID PRIMARY KEY,
    user_id UUID,
    schema_name TEXT,
    content TEXT, -- Storing JSON content as text
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

-- Table for storing actual content
CREATE TABLE IF NOT EXISTS cms.content_data (
    content_id UUID PRIMARY KEY,
    schema_id UUID,
    user_id UUID,
    content TEXT, -- Storing JSON content as text
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
