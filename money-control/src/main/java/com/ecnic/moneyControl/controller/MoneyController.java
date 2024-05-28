package com.ecnic.moneyControl.controller;

import com.ecnic.moneyControl.models.Money;
import com.ecnic.moneyControl.repository.MoneyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j // untuk log
@RequestMapping(value = "money")
public class MoneyController {

    //    @Autowired --> bisa tambahkan annotation ini tapi sudah tidak di rekomendasikan
    // atau gunakan annotation @RequiredArgsConstructor
    private final MoneyRepository moneyRepository;

    @GetMapping(value = "hello-world") // API : GET
    public String sayHelloWorld(){
        return "Hello World!";
    }

    @GetMapping(value = "hello/{name}") // API : GET + path variables (.../path_variables)
    public String sayHello(@PathVariable String name){
        return "Hello "+name;
    }

    @GetMapping
    public List<Money> moneyList (){
        return moneyRepository.findAll();
    }

    @GetMapping(value = "{id}")
    public Money moneyList (@PathVariable long id){
        return moneyRepository.findById(id).orElse(null);
    }

    @PostMapping(value = "save")
    public Money sayMoney(@RequestBody Money money){
        log.debug("Save Money : ",money);
        return moneyRepository.saveAndFlush(money);
    }

    // delete
    @RequestMapping(value = "delete/{id}",method = RequestMethod.DELETE)
    public void deleteMoney(@PathVariable long id){
        moneyRepository.deleteById(id);
    }

    @PutMapping(value = "update")
    public void updateMoney(
            @RequestParam long id,
            @RequestParam String itemName,
            @RequestParam BigDecimal price
    ){
        Money currentMoney = moneyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        /* harusnya seperti ini gak boleh (throw exception)
        kalau gak nemu kirim aja null.
        Karena ini bukan error, hanya kesalahan bisnis jadi null saja cukup.
        Disini cuma mencontohnya untuk dapat http status selain 200.
        */

        Money update = new Money();
        update.setItem(itemName);
        update.setPrice(price);

        BeanUtils.copyProperties(update, currentMoney, "id", "dateInput");
        moneyRepository.saveAndFlush(currentMoney);
    }

    // ada 3 cara inject dependency
    // 1. annotation @Autowired (udah gak direkomendasiin lagi sama spring)
    // 2. constructor (best practice yang di arahin sama spring)
    // 3. setter (cuma jarang dipake)


    /* kalau ada error ini : java.lang.NullPointerException: Cannot invoke "com.ecnic.moneyControl.repository.MoneyRepository.saveAndFlush(Object)" because "this.moneyRepository" is null
    "timestamp": "2024-05-27T07:56:15.252+00:00",
    "status": 500,
    "error": "Internal Server Error",
    "path": "/"

    saat mau exexute method post di postman, bisa jadi karna repository nya belum di inject.
    Cara inject nya ubah : private MoneyRepository moneyRepository; >>> private final MoneyRepository moneyRepository;

    saat pake final, akan di inject saat ngecreate bean
    kalau pakai yg bukan final, akan di inject tapi value nya berubah2
    kalau pakai final value nya gaakan berubah object nya
     */
}