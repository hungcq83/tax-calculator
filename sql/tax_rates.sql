-- Table: public.tax_rates

CREATE TABLE public.tax_rates
(
  id serial PRIMARY KEY,
  start_from integer,
  tax_per_dollar real,
  base_tax_amount integer
)

TABLESPACE pg_default;

INSERT INTO public.tax_rates(
start_from, tax_per_dollar, base_tax_amount)
VALUES
(0, 0, 0),
(18201,0.19,0),
(37001,0.325,3572),
(87001,0.37,19822),
(180001,0.45,54232)
;
