package com.ecnic.moneyControl.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity(name = "money")
@Data
public class Money {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String item;
    private BigDecimal price = new BigDecimal(0);
    private String dateInput;


    // jika tidak menggunakan anotatiom @Data (lombok) kita bisa menggunakan manual getter setter
//    // getter
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getItem() {
//        return item;
//    }
//
//    public BigDecimal getPrice() {
//        return price;
//    }
//
//    public ZonedDateTime getDateInput() {
//        return dateInput;
//    }
//
//
//    // setter
//
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setItem(String item) {
//        this.item = item;
//    }
//
//    public void setPrice(BigDecimal price) {
//        this.price = price;
//    }
//
//    public void setDateInput(ZonedDateTime dateInput) {
//        this.dateInput = dateInput;
//    }


// Anotation @Data akan mengenerate constarctor dari data di bawah,
//    contoh :
//    public Money(Long id, String item, BigDecimal price, ZonedDateTime dateInput) {
//        this.id = id;
//        this.item = item;
//        this.price = price;
//        this.dateInput = dateInput;
//    }

    /*
    GenerationType.IDENTITY
    kondisi: ketika delete suatu data / roe ditengah2, saat insert data kembali dia tidak akan mengisi data yg kosong ditengah tetapi melanjutkan baris paling akhir (paling baru).
    Kenapa?
    IDENTITY ini mengisi identitas terbaru tidak melihat data yg lama.
    Jadi kalau increment nya sudah naik, dia akan naik terus tidak akan melihat yang sebelum2nya.
    Artinya gak akan bisa diubah ke yg lama.
    Secara data gak bisa duplicate
     */
}
