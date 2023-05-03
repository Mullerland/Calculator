CREATE TABLE IF NOT EXISTS employee (
    id                                     BIGSERIAL PRIMARY KEY,
    first_name                             VARCHAR(255) NOT NULL,
    last_name                              VARCHAR(255) NOT NULL,
    company                                VARCHAR(255) NOT NULL

);


CREATE TABLE IF NOT EXISTS vacation_calculator_income (
    id                                      BIGSERIAL PRIMARY KEY,
    start_billing_period                    DATE,
    end_billing_period                      DATE,
    unaccounted_days_for_the_billing_period INT,
    income_for_the_billing_period           INT,
    start_date                              DATE,
    end_date                                DATE,
    unaccounted_days_for_the_vacation       INT,
    holiday_pay                             DOUBLE,
    em_key                                  BIGINT,
    employee_id                             BIGINT REFERENCES employee(id)

 );

