provider "sql" {
  alias = "postgres"
  url = "postgres://tax_calculator_user:${var.db_password}@seisma.c4g3rzhrb3oy.us-east-1.rds.amazonaws.com:5432/postgres?sslmode=disable"
}

resource "sql_migrate" "db" {
  provider = sql.postgres
  migration {
    id = "seed"
    up = <<SQL
CREATE TABLE public.tax_rates
(
  id serial PRIMARY KEY,
  start_from integer,
  tax_per_dollar real,
  base_tax_amount integer
);
SQL

    down = "DROP TABLE IF EXISTS public.tax_rates;"
  }

  migration {
    id = "seed"
    up   = <<SQL
    INSERT INTO public.tax_rates(
start_from, tax_per_dollar, base_tax_amount)
VALUES
(0, 0, 0),
(18201,0.19,0),
(37001,0.325,3572),
(87001,0.37,19822),
(180001,0.45,54232);
SQL
    down = "DELETE FROM public.tax_rates;"
  }
}