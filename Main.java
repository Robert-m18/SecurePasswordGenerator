import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;



public class Main {
    public static void main(String[] args) {
        generujHaslo();
    }

    //Tworzenie metody
    static void generujHaslo() {
        String literyMale = "mnbvcxzlkjhgfdsapoiuytrewq";
        String literyDuze = "MNBVCXZLKJHGFDSAPOIUYTREWQ";
        String znaki = "!@#$%^&*";
        String liczby = "1234567890";
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        boolean program = true;
        System.out.println("=== GENERATOR BEZPIECZNYCH HASEŁ ===");

        while (program) {
         //zabezpieczenie aby hasło miało odpowiednią długość
            System.out.println("Ile znaków ma mieć hasło");
            int haslo = sc.nextInt();
            if (haslo < 3) {
                System.out.println("Hasło musi mieć co najmniej 3 znaki!");
                continue;
            }
            if (haslo > 50) {
                System.out.println("Hasło jest za długie! Maksymalna długość to 50 znaków.");
                continue;
            }

            //uzgadnianie co ma zawierać hasło a czego nie
            boolean czyMale = pytanie(sc, "Czy hasło ma mieć małe litery?");
            boolean czyDuze = pytanie(sc, "Czy hasło ma mieć duże litery?");
            boolean czyznaki = pytanie(sc, "Czy hasło ma mieć znaki specjalne?");
            boolean czyLiczby = pytanie(sc, "Czy hasło ma mieć liczby?");

            //Budowanie Stringa zawierającego znaki które ma losowo wstawiać program do budowanego hasła
            StringBuilder znakiDoWyboru;
            znakiDoWyboru = new StringBuilder();
            if (czyMale) {
                znakiDoWyboru.append(literyMale);
            }
            if (czyDuze) {
                znakiDoWyboru.append(literyDuze);
            }
            if (czyznaki) {
                znakiDoWyboru.append(znaki);
            }
            if (czyLiczby) {
                znakiDoWyboru.append(liczby);
            }
            if (znakiDoWyboru.isEmpty()) {
                System.out.println("Nie wybrano żadnego typu znaków! Spróbuj ponownie.");
                continue;
            }

            StringBuilder sklejoneHaslo = new StringBuilder();

            //Budowa hasła
            for (int i = 0; i < haslo; i++) {

                sklejoneHaslo.append(znakiDoWyboru.charAt(random.nextInt(znakiDoWyboru.length())));

            }

            //wypisanie hasła
            System.out.println("Twoje wygenerowane hasło to: " + sklejoneHaslo);

            //Sprawdzanie siły hasła
            if (sklejoneHaslo.length() <= 3) {
                System.out.println("Hasło jest bardzo słabe");
            }
            if (sklejoneHaslo.length() <= 8) {
                System.out.println("Hasło jest słabe");
            } else if (czyMale && czyDuze && !czyznaki && !czyLiczby) {
                System.out.println("Hasło jest średnie");
            } else if (czyMale && czyDuze && czyznaki && czyLiczby) {
                System.out.println("Hasło jest silne");
            } else {
                System.out.println("Hasło jest średnie");
            }
            try (FileWriter writer = new FileWriter("hasla.txt", true)){
                writer.write("[" + java.time.LocalDateTime.now() +sklejoneHaslo + "]"+ "\n");
            } catch (IOException e) {
                System.out.println("Błąd zapisu do pliku: " + e.getMessage());
            }
            boolean kontynuuj = true;
            System.out.println(" ");
            while (kontynuuj) {
                System.out.println("Czy stworzyć kolejne hasło? (Tak/Nie)");
                String takNie = sc.next();
                if (takNie.equalsIgnoreCase("Tak")) {
                    kontynuuj = false;
                } else if (takNie.equalsIgnoreCase("Nie")) {
                    program = false;
                    kontynuuj = false;
                    System.out.println("Zamknięto Generator haseł");
                    try (FileWriter writer = new FileWriter("hasla.txt", true)){
                        writer.write("-------------------------------\n");
                    } catch (IOException e) {
                        System.out.println("Błąd zapisu do pliku: " + e.getMessage());}

                } else {
                    System.out.println("Wprowadzono coś innego niż opcja (Tak/Nie) spróbuj ponownie");

                }
            }

        }


    }

    //metoda uzgadniająca co ma znajdować się w haśle
    static boolean pytanie(Scanner sc, String tekst) {
        boolean poprawnie = true;
        String odp = "";
        while (poprawnie) {
            System.out.println(tekst + " (tak/nie)");
             odp = sc.next();
            if (odp.equalsIgnoreCase("Tak")) {
                poprawnie = false;
            }
            else if (odp.equalsIgnoreCase("Nie")) {
                poprawnie = false;
            }
            else {
                System.out.println("Wprowadzona opcja jest inna niż (Tak/Nie) spróbuj ponownie");
            }
            
        }
        return odp.equalsIgnoreCase("Tak");
    }
    
}





