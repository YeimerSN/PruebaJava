package com.pruebaBack.PruebaBackDesarrolladorJr.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    String name;
    Integer priceFinal;
    String description;
    Integer categoryId;
    String imagenUrl;
}
