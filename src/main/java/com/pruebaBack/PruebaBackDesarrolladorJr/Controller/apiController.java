package com.pruebaBack.PruebaBackDesarrolladorJr.Controller;

import com.pruebaBack.PruebaBackDesarrolladorJr.Model.Product;
import com.pruebaBack.PruebaBackDesarrolladorJr.Model.SendProduct;
import jakarta.websocket.OnClose;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/nexsys/v1/")
public class apiController {
    @Value("${spring.uri.api}")
    private String apiUrl;
    private final RestTemplate restTemplate;
    public apiController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts(){
        //Realizando la solicitud a la api
        ResponseEntity<?> responseEntity = restTemplate.getForEntity(apiUrl + "/products", List.class);

        //Verificando solicitud exitosa
        if(responseEntity.getStatusCode().is2xxSuccessful()){
            //Obtener la respuesta
            List<Map<String, Object>> response = (List<Map<String, Object>>) responseEntity.getBody();

            //Creacion del objeto solicitado
            List<Map<String, Object>> camposRequeridosList = new ArrayList<>();

            //Extrayendo los campos requeridos
            for(Map<String, Object> objectMap: response){
                int pid = Integer.parseInt(objectMap.get("id").toString());
                String name = objectMap.get("title").toString();
                int priceFinal = Integer.parseInt(objectMap.get("price").toString());
                String description = objectMap.get("description").toString();

                //Objeto con los datos
                Map<String, Object> camposRequeridos = new HashMap<>();
                camposRequeridos.put("pid", pid);
                camposRequeridos.put("name", name);
                camposRequeridos.put("priceFinal", priceFinal);
                camposRequeridos.put("description", description);
                camposRequeridosList.add(camposRequeridos);
            }
            return ResponseEntity.ok(camposRequeridosList);
        }else{
            return ResponseEntity.status(responseEntity.getStatusCode()).body("Error al obtener los datos del API (Productos)");
        }
    }
    @GetMapping("/categories")
    public ResponseEntity<?> getAllCategories(){
        //Realizando la solicitud a la api
        ResponseEntity<?> responseEntity = restTemplate.getForEntity(apiUrl + "/categories", List.class);

        if(responseEntity.getStatusCode().is2xxSuccessful()){
            //Obtener la respuesta
            List<Map<String, Object>> response = (List<Map<String, Object>>) responseEntity.getBody();
            //Creacion del objeto solicitado
            List<Map<String, Object>> camposRequeridosList = new ArrayList<>();
            //Extrayendo los campos requeridos
            for(Map<String, Object> objectMap: response){
                int cid = Integer.parseInt(objectMap.get("id").toString());
                String title = objectMap.get("name").toString();

                //Objeto con los datos
                Map<String, Object> camposRequeridos = new HashMap<>();
                camposRequeridos.put("cid", cid);
                camposRequeridos.put("title", title);
                camposRequeridosList.add(camposRequeridos);
            }
            return ResponseEntity.ok(camposRequeridosList);
        }else{
            return ResponseEntity.status(responseEntity.getStatusCode()).body("Error al obtener los datos del API (Categorias)");
        }
    }

    @PostMapping("/products")
    public ResponseEntity<?> createProduct(@RequestBody Product product){
        //Construnccion del objeto a enviar
        SendProduct sendProduct = new SendProduct();


        sendProduct.setTitle(product.getName());
        sendProduct.setPrice(product.getPriceFinal());
        sendProduct.setDescription(product.getDescription());
        sendProduct.setCategoryId(product.getCategoryId());

        //Creando la lista de las url
        List<String> url = new ArrayList<>();
        url.add(product.getImagenUrl());
        sendProduct.setImages(url);
        try {
            ResponseEntity<Map> responseEntity = restTemplate.postForEntity(apiUrl + "/products", sendProduct, Map.class);
            //Enviando el objeto
            if(responseEntity.getStatusCode().is2xxSuccessful()){
                //Onteniendo el body del responseEntity
                Map<String, Object> responseBody = responseEntity.getBody();

                //Extrayendo el valor de la respuesta
                Integer pid = (Integer) responseBody.get("id");
                Map<String, Object> respuesta = new HashMap<>();

                //Generando la respuesta solicitada
                respuesta.put("pid", pid);
                return ResponseEntity.ok(respuesta);
            }else{
                return ResponseEntity.status(responseEntity.getStatusCode()).body("Error al crear el producto");
            }
        }catch (Exception ex){
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Entrada no valida, verifica el objeto!");
            response.put("status", HttpStatus.BAD_REQUEST);
            response.put("description", "Bad Request");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

}
