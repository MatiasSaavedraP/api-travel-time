package com.pruebatecnica.api_travel_time.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import com.pruebatecnica.api_travel_time.service.RouteLegService;
import java.util.List;
import com.pruebatecnica.api_travel_time.model.RouteLeg;

@RestController
@RequestMapping("/api/route-legs")
public class RouteLegController {

    @Autowired
    private RouteLegService routeLegService;

    @GetMapping("/get-all")
    public List<RouteLeg> getAllRouteLegs(){
        return routeLegService.getRougeLegs();
    }

    @PostMapping("/import-csv")
    public ResponseEntity<String> importCsv(@RequestParam("file") MultipartFile file) {
        try {
            routeLegService.importRouteLegFromCsv(file);
            return ResponseEntity.ok("Segmentos de ruta cargados correctamente.");
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return ResponseEntity.badRequest().body("Error al importar el archivo de segmentos de ruta: " + e.getMessage());
        }
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<String> handleMultipartException(MultipartException ex) {
        return ResponseEntity.badRequest()
            .body("Error: Debe enviar un archivo usando 'multipart/form-data' con el campo 'file'.");
    }

}