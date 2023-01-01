package com.garanti.FirstSpringWeb.controller;

import com.garanti.FirstSpringWeb.model.Ogretmen;
import com.garanti.FirstSpringWeb.model.Person;
import com.garanti.FirstSpringWeb.repo.OgretmenRepo;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping(path = "ogretmen")
public class OgretmenController {

    // localhost:9090/FirstSpringWeb/ogretmen

   /* public OgretmenController(){
        //new Person(1,"fg").toString(); lombok ile oluşturduk
        System.err.println("----------------> new yapılıyor");
    }

    @GetMapping(path = "hello")
    public String helloSpring()
    {
        // new OgretmenRepo().helloSpring();
        // localhost:9090/FirstSpringWeb/ogretmen/hello
        return "Hello Spring";
    }*/

    private OgretmenRepo repo;

    public OgretmenController() {
        this.repo = new OgretmenRepo();
    }

    @GetMapping(path = "getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Ogretmen>> getAll() {
        // localhost:9090/FirstSpringWeb/ogretmen/getAll
        ArrayList<Ogretmen> res = repo.getAll();
        if (res == null || res.size() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok(res);
        }
    }

    @GetMapping(path = "getByIdHeader", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ogretmen> getByIdHeader(@RequestHeader(name = "id") Integer id) {
        // localhost:9090/FirstSpringWeb/ogretmen/getById?id=1
        Ogretmen res = repo.getById(id);
        if (res != null) {
            return ResponseEntity.ok(res);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping(path = "getById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ogretmen> getByIdQueryParam(@RequestParam(value = "id", required = true) Integer id) {
        // localhost:9090/FirstSpringWeb/ogretmen/getById?id=1
        Ogretmen res = repo.getById(id);
        if (res != null) {
            return ResponseEntity.ok(res);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping(path = "getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ogretmen> getByIdPathParam(@PathVariable(value = "id") Integer id) {
        // localhost:9090/FirstSpringWeb/ogretmen/getById/1
        Ogretmen res = repo.getById(id);
        if (res != null) {
            return ResponseEntity.ok(res);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PostMapping(path = "save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@RequestBody Ogretmen ogretmen) {
        // localhost:9090/FirstSpringWeb/ogretmen/save
        // {"name":"RestTest", "is_GICIK": true}
        if (repo.save(ogretmen)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Başarı ile kaydedildi");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Başarı ile kaydedildi");
        }
    }

    @DeleteMapping(path = "deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(value = "id") Integer id) {
        // localhost:9090/FirstSpringWeb/ogretmen/deleteById/1
        if (repo.deleteById(id)) {
            return ResponseEntity.ok("Başarı ile silindi");
        } else {
            return ResponseEntity.internalServerError().body("Başarı ile silinemedi");
        }
    }

    @DeleteMapping(path = "deleteByIdHeader")
    public ResponseEntity<String> deleteByIdHeader(@RequestHeader(value = "id") Integer id) {
        // localhost:9090/FirstSpringWeb/ogretmen/deleteById/1
        if (repo.deleteById(id)) {
            return ResponseEntity.ok("Başarı ile silindi");
        } else {
            return ResponseEntity.internalServerError().body("Başarı ile silinemedi");
        }
    }


}
