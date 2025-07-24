-- USERS table


CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    product_id VARCHAR(100) NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    product_description TEXT,
    interest_rate DOUBLE PRECISION NOT NULL,
    version INTEGER NOT NULL,
    status VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    effective_start_date TIMESTAMP  -- ✅ Newly added column
);



-- PRODUCT VERSIONS table
CREATE TABLE IF NOT EXISTS product_version (
    id SERIAL PRIMARY KEY,
    version_number VARCHAR(10) NOT NULL,
    interest_rate DECIMAL(5,2) NOT NULL,
    effective_start_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    product_id INTEGER NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(id)
);
