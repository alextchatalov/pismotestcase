-- Create the accounts table
CREATE TABLE IF NOT EXISTS accounts
(
    account_id
    VARCHAR
    PRIMARY
    KEY,
    document_number
    VARCHAR
(
    50
) NOT NULL
    );

-- Create an index for Account_ID
CREATE INDEX idx_account_id ON accounts (account_id);


-- Create the operation_type table
CREATE TABLE IF NOT EXISTS operation_type
(
    Operation_type_id
    INT
    PRIMARY
    KEY,
    description
    VARCHAR
(
    50
) NOT NULL
    );

-- Insert the specified rows into the operation_type table
INSERT INTO operation_type (operation_type_id, description)
VALUES (1, 'COMPRA A VISTA'),
       (2, 'COMPRA PARCELADA'),
       (3, 'SAQUE'),
       (4, 'PAGAMENTO');

CREATE INDEX idx_operation_type_id ON operation_type (operation_type_id);

-- Create the transaction table
CREATE TABLE transaction
(
    transaction_id    VARCHAR(36)                         NOT NULL PRIMARY KEY, -- UUID for transaction_id
    account_id        VARCHAR                             NOT NULL,             -- Foreign key to accounts table
    operation_type_id INT                                 NOT NULL,             -- Foreign key to operation_type table
    amount            DECIMAL(19, 4)                      NOT NULL,             -- High-precision BigDecimal value
    event_date        TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,             -- Date and time of the event

    -- Foreign key constraints
    CONSTRAINT fk_transaction_account FOREIGN KEY (account_id) REFERENCES accounts (account_id),
    CONSTRAINT fk_transaction_operation FOREIGN KEY (operation_type_id) REFERENCES operation_type (operation_type_id)
);

-- Create an index for transaction_id
CREATE INDEX idx_transaction_id ON transaction (transaction_id);

-- Create an index for account_id
CREATE INDEX idx_transaction_account_id ON transaction (account_id);