CREATE TABLE weather
(
    id                      serial PRIMARY KEY,
    time_of_record_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP(0),
    name_of_city            character varying,
    temperature             float
);