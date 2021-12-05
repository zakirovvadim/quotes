CREATE DATABASE quote_table;

CREATE TABLE quote
(
    id   SERIAL PRIMARY KEY,
    isin CHARACTER VARYING(30),
    bid  DECIMAL,
    ask  DECIMAL,
    is_Actual BOOLEAN,
    elvl DECIMAL
);