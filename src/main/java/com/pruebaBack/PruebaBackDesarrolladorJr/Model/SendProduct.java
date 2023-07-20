package com.pruebaBack.PruebaBackDesarrolladorJr.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SendProduct {
    String title;
    Integer price;
    String description;
    Integer categoryId;
    List<String> images;
}
