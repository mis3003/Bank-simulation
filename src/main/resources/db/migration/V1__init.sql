CREATE TABLE bank_stock (
                            id BIGSERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL UNIQUE,
                            quantity INTEGER NOT NULL
);

CREATE TABLE wallet (
                        id VARCHAR(255) PRIMARY KEY
);

CREATE TABLE wallet_stock (
                              id BIGSERIAL PRIMARY KEY,
                              wallet_id VARCHAR(255) NOT NULL REFERENCES wallet(id),
                              stock_name VARCHAR(255) NOT NULL,
                              quantity INTEGER NOT NULL
);

CREATE TABLE audit_log (
                           id BIGSERIAL PRIMARY KEY,
                           type VARCHAR(50) NOT NULL,
                           wallet_id VARCHAR(255) NOT NULL,
                           stock_name VARCHAR(255) NOT NULL
);