
CREATE SEQUENCE public.category_id_seq;

CREATE TABLE public.category (
                id INTEGER NOT NULL DEFAULT nextval('public.category_id_seq'),
                name VARCHAR NOT NULL,
                CONSTRAINT category_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.category_id_seq OWNED BY public.category.id;

CREATE SEQUENCE public.product_id_seq;

CREATE TABLE public.product (
                id INTEGER NOT NULL DEFAULT nextval('public.product_id_seq'),
                name VARCHAR NOT NULL,
                label VARCHAR NOT NULL,
                price DOUBLE PRECISION NOT NULL,
                productor_id INTEGER NOT NULL,
                picture VARCHAR NOT NULL,
                category_id INTEGER NOT NULL,
                stock_id INTEGER NOT NULL,
                CONSTRAINT product_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;

ALTER TABLE public.product ADD CONSTRAINT category_product_fk
FOREIGN KEY (category_id)
REFERENCES public.category (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
