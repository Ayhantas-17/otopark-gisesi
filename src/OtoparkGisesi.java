import java.util.Scanner;

public class OtoparkGisesi
{
    static Scanner sc = new Scanner(System.in);


    static int dakika(String mesaj) {
        System.out.print(mesaj);
        String[] p = sc.nextLine().split(":");
        return Integer.parseInt(p[0].trim()) * 60 + Integer.parseInt(p[1].trim());
    }


    static int ucret(int dk) {
        int gun = dk / 1440, kalan = dk % 1440, u;
        if (kalan <= 15) u = 0;
        else if (kalan <= 60) u = 60;
        else if (kalan <= 180) u = 100;
        else if (kalan <= 360) u = 150;
        else u = Math.min(150 + 30 * (int) Math.ceil((kalan - 360) / 60.0), 400);
        return gun * 400 + u;
    }

    public static void main(String[] args) {
        int adet = 0, ucretsiz = 0, enUzun = -1;
        double kasa = 0;

        while (true) {
            System.out.print("Tür (1-Otomobil 2-Motosiklet 3-Minibüs, 0-Bitir): ");
            String tur = sc.nextLine().trim();
            if (tur.equals("0")) break;

            double carpan = tur.equals("2") ? 0.5 : tur.equals("3") ? 1.5 : 1.0;
            int giris = dakika("Giriş ");
            int cikis = dakika("Çıkış");

            if (cikis < giris) cikis += 1440;      // gece yarısı
            int sure = cikis - giris;
            double toplam = ucret(sure) * carpan;

            adet++;
            kasa += toplam;
            if (toplam == 0) ucretsiz++;
            if (sure > enUzun) enUzun = sure;

            System.out.println("Ücret: " + toplam + " TL");
        }

        System.out.println("\n--- VARDİYA SONU ---");
        System.out.println("Araç sayısı     : " + adet);
        System.out.println("Ücretsiz çıkış  : " + ucretsiz);
        System.out.println("En uzun kalış   : " + enUzun / 60 + " saat " + enUzun % 60 + " dk");
        System.out.println("Kasa            : " + kasa + " TL");
    }
}



