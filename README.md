# Prueba BackEnd Desarrollador Jr

Autor: *Yeimer Serrano Navarro*

## Descripción del proyecto

El proyecto consistia en la creación de tres endpoints haciendo uso de los servicios expuestos en __https://fakeapi.platzi.com/doc/introduction__, los servicios web se tenian que desarrollar con Java, para este caso se hizo uso de las siguientes herramientas.

### Herramientas usadas
* SpringBoot
* Java 17
* Maven 3.9.3

El servicio web se encuentra desplegado en siguiente enlace __https://yeimerserranobackendjava.onrender.com__ y el cual cuenta con los siguientes endpoints.

* GetAllProducts, accediendo a __/nexsys/v1/products__

Este endpoint lista todos los productos que se encunetran creados, en el formato especifico de la prueba, cuya tiene la siguiente estructura.

```json
{
    "pid": "id_del_producto_(numerico)",
    "name": "nombre del producto",
    "priceFinal": "valor_del_producto_(numerico)",
    "description": "descricion del producto"
}
```

![Ejemplo GetAllProducts](https://github.com/YeimerSN/PruebaJava/blob/main/src/main/resources/static/AllProducts.png)

* GetAllCategories, accedido a __/nexsys/v1/categories__

Este endpoint permite listar todas las categorias de los productos que se encuntran disponibles, y la respuesta del endpoint es de la siguiente manera.

```json
{
    "cid": "id_categoria_(numerico)",
    "title": "nombre categoria"
}
```

![Ejemplo GetAllCategories](https://github.com/YeimerSN/PruebaJava/blob/main/src/main/resources/static/AllCategories.png)

* PostPorducts, accediendo a __/nexsys/v1/products__

Este endpoint solicita un objeto json de la siguiente manera:

```json
{
  "name": "nombre del producto",
  "priceFinal": "valorNumerico",
  "description": "descripcion del producto",
  "categoryId": "identificador de la categoria (numerico)",
  "imagenUrl": "ruta de la imagen"
}
```

Y la respuesta una vez guardado el producto es como se muestra a continuación:

```json
{
  "pid": "id_del_producto_(numerico)"
}
```

![Ejemplo PostProduct](https://github.com/YeimerSN/PruebaJava/blob/main/src/main/resources/static/PostProduct.png)
