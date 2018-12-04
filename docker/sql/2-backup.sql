INSERT INTO public.category (id, name) VALUES (1, 'fruit');
INSERT INTO public.category (id, name) VALUES (2,'légume');
INSERT INTO public.category (id, name) VALUES (3,'fromage');

INSERT INTO public.product (id, name,label,price,productor_id,picture,stock_id,category_id) VALUES (1, 'cabecou','bon fromage de chèvre',14,1,'cabecou.jpg',1,3);
INSERT INTO public.product (id, name,label,price,productor_id,picture,stock_id,category_id) VALUES (2, 'tomate','tomate cerise',12,2,'tomate.jpg',2,2);