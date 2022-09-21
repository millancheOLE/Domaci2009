package org.example;

/*
Napraviti fajl domaci22.xslx i u prve dve kolone upisati svoje ime pa prezime, u drugom redu upisati nasumicno ime i prezime.
Napisati metodu u javi koja ce ispisati ta 2 imena i prezimena.
Napisati metodu koja ce upisivati u xslx fajl u prvu i drugu kolonu ime i prezime.
Treba da dodate jos 8 nasumicno generisanih imena i prezimena koriscenjem biblioteke Java Faker.
Opet ih ispisati koristeci metodu za ispisivanje.
Metodu za ispisivanje napraviti da radi tako da kada dobijete prazane vrednosti u redu da prestane petlja.
Za kolone mozete fiksirati samo 2 kolone A i B.
Upisivanje ne mora da bude dinamicki, moze samo da upisuje od treceg reda (drugi index) do 10og reda.

Koristiti maven i strukturu gde se sta nalazi kao na predavanju sto je.

Domaci kao i prosli put uploadovati na github, imenovati “domaci22” i to je to, ne morate da share link ako ste mi vec
poslali invite za collaborator ili link na thread (iz prethodnog domaceg).
 */

import com.github.javafaker.Faker;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        System.err.println("----------------------");
        System.err.println("Stampanje imena i prezimena PRE fakera.");
        write();    // Stampanje druga dva imena i prezimena unesena kroz metodu za ispis.
        print();    // Stampanje imena i prezimena pre fakera.
        System.err.println("----------------------");

        Faker faker = new Faker();  // Kreiranje objekta "faker" iz klase "Faker" koji se prosledjuje metodi kao parametar.
        writeFaker(faker);  // Prosledjivanje objekta metodi za dalju obradu random imena i prezimena za upis u Excel fajl.
        System.err.println("----------------------");
        System.err.println("Stampanje imena i prezimena POSLE fakera.");
        print();    // Ako se kompajliranje pokrece drugi put, u prvoj stampi stampace i rezultate iz prethodnog generisanja (regularno + faker).
        // Prvo sam radio posebnu metodu koja je stampala samo faker i nije imala ovaj problem, ali sam je izbrisao jer sam video da pise da se koristi ista metoda
        // i da se samo ogranici "null", odnosno gde prestaje stampa.
        System.err.println("----------------------");

    }

    public static void print() {

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("domaci22.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheet("Sheet1");

            for (int i = 0; i < 100; i++) {
                XSSFRow row = sheet.getRow(i);
                if (row == null) {
                    break;
                } else {
                    for (int j = 0; j < 2; j++) {
                        XSSFCell cell = row.getCell(j);
                        System.out.print(cell.getStringCellValue() + " ");
                    }
                    System.out.println();
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void write() throws IOException {

        FileInputStream fileInputStream = new FileInputStream("domaci22.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheet("Sheet1");

        for (int i = 0; i < 1; i++) {
            XSSFRow row = sheet.createRow(i);
            for (int j = 0; j < 1; j++) {
                XSSFCell cell = row.createCell(j);
                cell.setCellValue("Milan");
            }
            for (int j = 1; j < 2; j++) {
                XSSFCell cell = row.createCell(j);
                cell.setCellValue("Stanojevic");
            }
        }

        for (int i = 1; i < 2; i++) {
            XSSFRow row = sheet.createRow(i);
            for (int j = 0; j < 1; j++) {
                XSSFCell cell = row.createCell(j);
                cell.setCellValue("Milos");
            }
            for (int j = 1; j < 2; j++) {
                XSSFCell cell = row.createCell(j);
                cell.setCellValue("Garunovic");
            }
        }
        FileOutputStream fileOutputStream = new FileOutputStream("domaci22.xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }

    public static void writeFaker(Faker faker) throws IOException {

        FileInputStream fileInputStream = new FileInputStream("domaci22.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheet("Sheet1");

        for (int i = 2; i < 10; i++) {
            XSSFRow row = sheet.createRow(i);
            for (int j = 0; j < 1; j++) {
                XSSFCell cell = row.createCell(j);
                cell.setCellValue(faker.name().firstName());
            }
            for (int j = 1; j < 2; j++) {
                XSSFCell cell = row.createCell(j);
                cell.setCellValue(faker.name().lastName());
            }
        }
        FileOutputStream fileOutputStream = new FileOutputStream("domaci22.xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }
}