/*idUsuario - email - nombre - password - direccion - telefono*/
insert into usuario values (1, "jhon@gmail.com", "Jhon", "$2a$10$RcMreAWaQiP2AJ46pBPVv./L1t0KMp2u34NaZNAWD/S6Glod..WjW", "calle 12", "7415896");
insert into usuario values (2, "luis@gmail.com", "Luis", "$2a$10$RcMreAWaQiP2AJ46pBPVv./L1t0KMp2u34NaZNAWD/S6Glod..WjW" , "calle 15 # 8", "7456712");
insert into usuario values (3, "carlos@gmail.com", "Carlos", "$2a$10$RcMreAWaQiP2AJ46pBPVv./L1t0KMp2u34NaZNAWD/S6Glod..WjW" , "calle 20", "743954");
insert into usuario values (4, "diego@gmail.com", "Diego", "$2a$10$RcMreAWaQiP2AJ46pBPVv./L1t0KMp2u34NaZNAWD/S6Glod..WjW" , "calle 1 # 16", "7403718");
insert into usuario values (5,  "juan@gmail.com", "Juan", "$2a$10$RcMreAWaQiP2AJ46pBPVv./L1t0KMp2u34NaZNAWD/S6Glod..WjW", "calle 5 # 11", "7475982");

/*idModerador - email - nombre - password*/
insert into moderador values (1, "pedro@gmail.com", "Pedro", "$2a$10$RcMreAWaQiP2AJ46pBPVv./L1t0KMp2u34NaZNAWD/S6Glod..WjW");
insert into moderador values (2, "jose@gmail.com", "Jose", "$2a$10$RcMreAWaQiP2AJ46pBPVv./L1t0KMp2u34NaZNAWD/S6Glod..WjW");
insert into moderador values (3, "frank@gmail.com", "Frank", "$2a$10$RcMreAWaQiP2AJ46pBPVv./L1t0KMp2u34NaZNAWD/S6Glod..WjW");
insert into moderador values (4, "fabian@gmail.com", "Fabian", "$2a$10$RcMreAWaQiP2AJ46pBPVv./L1t0KMp2u34NaZNAWD/S6Glod..WjW");
insert into moderador values (5, "felipe@gmail.com",  "Felipe", "$2a$10$RcMreAWaQiP2AJ46pBPVv./L1t0KMp2u34NaZNAWD/S6Glod..WjW");

/*idProducto - activo - descripcion - fechaCreado - fechaLimite - nombre - precio - unidades - idVendedor*/
insert into producto values (1, "ACTIVO", "el mejor televisor", '2023-02-08', '2023-04-08', "Televisor Sony", 3000000, 5,  1);
insert into producto values (2, "ACTIVO", "los mejores tenis", '2023-06-08', '2023-06-08', "Tenis Nike", 500000, 8, 4);
insert into producto values (3, "ACTIVO", "carro de segunda", '2023-02-08', '2023-04-08', "Spark GT", 20000000, 7, 1);
insert into producto values (4, "INACTIVO", "camisa de calidad", '2023-06-08', '2023-06-08', "Camisa Arturo Calle", 100000, 6, 5);
insert into producto values (5, "ACTIVO", "los segundos mejores tenis", '2023-06-08', '2023-06-08', "Tenis Adidas", 450000, 3, 4);

/*idProducto - categoria*/
insert into producto_categoria values (4, "ROPA");
insert into producto_categoria values (1, "TECNOLOGIA");
insert into producto_categoria values (3, "VEHICULO");
insert into producto_categoria values (2, "ZAPATOS");
insert into producto_categoria values (5, "ZAPATOS");

/*idProducto - urlimagen - imagenkey*/
insert into producto_imagen values (1, "https://res.cloudinary.com/dfqd0ixdc/image/upload/v1682484847/co/edu/uniquindio/unimarket/Unimarket/zl93gily8hg8gbhy9lek.jpg", "co/edu/uniquindio/unimarket/Unimarket/zl93gily8hg8gbhy9lek");
insert into producto_imagen values (1, "https://res.cloudinary.com/dfqd0ixdc/image/upload/v1682484847/co/edu/uniquindio/unimarket/Unimarket/zl93gily8hg8gbhy9lek.jpg", "co/edu/uniquindio/unimarket/Unimarket/zl93gily8hg8gbhy9lek");
insert into producto_imagen values (2, "https://res.cloudinary.com/dfqd0ixdc/image/upload/v1682484847/co/edu/uniquindio/unimarket/Unimarket/zl93gily8hg8gbhy9lek.jpg", "co/edu/uniquindio/unimarket/Unimarket/zl93gily8hg8gbhy9lek");
insert into producto_imagen values (2, "https://res.cloudinary.com/dfqd0ixdc/image/upload/v1682484847/co/edu/uniquindio/unimarket/Unimarket/zl93gily8hg8gbhy9lek.jpg", "co/edu/uniquindio/unimarket/Unimarket/zl93gily8hg8gbhy9lek");
insert into producto_imagen values (3, "https://res.cloudinary.com/dfqd0ixdc/image/upload/v1682484847/co/edu/uniquindio/unimarket/Unimarket/zl93gily8hg8gbhy9lek.jpg", "co/edu/uniquindio/unimarket/Unimarket/zl93gily8hg8gbhy9lek");
insert into producto_imagen values (3, "https://res.cloudinary.com/dfqd0ixdc/image/upload/v1682484847/co/edu/uniquindio/unimarket/Unimarket/zl93gily8hg8gbhy9lek.jpg", "co/edu/uniquindio/unimarket/Unimarket/zl93gily8hg8gbhy9lek");
insert into producto_imagen values (4, "https://res.cloudinary.com/dfqd0ixdc/image/upload/v1682484847/co/edu/uniquindio/unimarket/Unimarket/zl93gily8hg8gbhy9lek.jpg", "co/edu/uniquindio/unimarket/Unimarket/zl93gily8hg8gbhy9lek");
insert into producto_imagen values (4, "https://res.cloudinary.com/dfqd0ixdc/image/upload/v1682484847/co/edu/uniquindio/unimarket/Unimarket/zl93gily8hg8gbhy9lek.jpg", "co/edu/uniquindio/unimarket/Unimarket/zl93gily8hg8gbhy9lek");
insert into producto_imagen values (5, "https://res.cloudinary.com/dfqd0ixdc/image/upload/v1682484847/co/edu/uniquindio/unimarket/Unimarket/zl93gily8hg8gbhy9lek.jpg", "co/edu/uniquindio/unimarket/Unimarket/zl93gily8hg8gbhy9lek");
insert into producto_imagen values (5, "https://res.cloudinary.com/dfqd0ixdc/image/upload/v1682484847/co/edu/uniquindio/unimarket/Unimarket/zl93gily8hg8gbhy9lek.jpg", "co/edu/uniquindio/unimarket/Unimarket/zl93gily8hg8gbhy9lek");

/*
* codigo del usuario y del producto
*/
insert into favorito values (1, 1);
insert into favorito values (2, 2);
insert into favorito values (4, 3);
insert into favorito values (5, 4);
insert into favorito values (3, 1);

/*idComentario - fechaCreado - comentario - idProducto - idUsuario*/
insert into comentario values (1, '2023-06-08', "Me gusta mucho este televisor", 2, 5);
insert into comentario values (2, '2023-06-08', "Tiene envíos a la ciudad de Armenia", 4, 1);
insert into comentario values (3, '2023-06-08', "Me gusta más o menos este televisor", 3, 4);
insert into comentario values (4, '2023-06-08', "Me gusta poco este televisor", 1, 3);
insert into comentario values (5, '2023-06-08', "No me gusta mucho este televisor", 5, 2);

/*idProductoModerador - estado - fecha - motivo - idModerador - idProducto*/
insert into producto_moderador values (1, "APROBADO", '2023-06-08', "Cumple con todo", 1, 1);
insert into producto_moderador values (2, "RECHAZADO", '2023-06-08', "No cumple con con todo", 2, 2);
insert into producto_moderador values (3, "RECHAZADO", '2023-06-08', "No cumple con con todo", 4, 2);
insert into producto_moderador values (4, "APROBADO", '2023-06-08', "Cumple con con todo", 5, 3);
insert into producto_moderador values (5, "APROBADO", '2023-06-08', "Cumple con con todo", 3, 1);

/*idCompra - fechaCreado - metodopago - precio - idProducto*/
insert into compra values (1, '2023-06-08', "TARJETA_CREDITO", 900000, 1);
insert into compra values (2, '2023-06-08', "TARJETA_DEBITO", 3000000, 4);
insert into compra values (3, '2023-06-08', "TARJETA_CREDITO", 6000000, 1);
insert into compra values (4, '2023-06-08', "EFECTY", 1500000, 3);
insert into compra values (5, '2023-06-08', "TARJETA_CREDITO", 500000, 5);

/* idDT - precioProducto - unidades - compra_id - producto_id */
insert into detalle_compra values (1, 3000000, 2, 1, 1);
insert into detalle_compra values (2, 3000000, 1, 2, 1);
insert into detalle_compra values (3, 3000000, 2, 3, 1);
insert into detalle_compra values (4, 500000, 3, 4, 2);
insert into detalle_compra values (5, 500000, 1, 5, 2);

/*idCalificacion - puntuacion - idProducto - idUsuario*/
insert into calificacion values (1, 5, 3, 1);
insert into calificacion values (2, 4, 1, 2);
insert into calificacion values (3, 1, 4, 5);
insert into calificacion values (4, 5, 3, 4);
insert into calificacion values (5, 2, 5, 3);

/*idCentroAyuda - fechaCreado - mensaje - idModerador - idUsuario*/
insert into centro_ayuda values (1, '2023-06-08', "El producto aun no llega, que pasa??", 1, 2);
insert into centro_ayuda values (2, '2023-06-08', "El producto salio en mal estado", 3, 1);
insert into centro_ayuda values (3, '2023-06-08', "No llego el producto que compre", 4, 5);
insert into centro_ayuda values (4, '2023-06-08', "El producto aun no llega, que pasa??", 2, 4);
insert into centro_ayuda values (5, '2023-06-08', "El producto salio en mal estado", 1, 3);



