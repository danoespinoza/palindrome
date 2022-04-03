# Palindrome challenge in Spring Boot

## Build and test

Just run `docker-compose up` and enjoy testing!

- GET http://localhost:8080/api/getProducts
- GET http://localhost:8080/api/getProducts/181
- GET http://localhost:8080/api/getProducts/sdfds

Note: This project does not implement web interface.

## The Challenge

Líder quiere realizar una nueva campaña a través del sitio, pero esta ocasión quiere que sea
especial y que sea una sorpresa para sus clientes.
La campaña consiste en que cada vez que un cliente busque con un palíndromo, todos los
productos encontrados tendrán un 50% de descuento, esta búsqueda aplica para
identificadores (Id de Producto), marcas y descripciones de productos.

Consideraciones:
- Se necesita una API que provea el servicio de búsqueda para listar los productos
encontrados desde una base de datos en MongoDB (https://github.com/walmartdigital/products-db)
- En caso de que la búsqueda sea un palíndromo se deberá retornar los productos
con el descuento (50%) ya aplicado al precio
- Cuando sea una búsqueda sobre los identificadores de productos, se deberá
retornar el resultado exacto, es decir, un producto
- Para la marca y para la descripción de productos, basta con que la búsqueda sea de
más de 3 caracteres y que éstos estén incluidos en los campos ya mencionados
(marca y descripción).
- Opcionalmente, se necesita una aplicación web que consuma la API descrita y que
contenga un buscador y una sección de resultados.
