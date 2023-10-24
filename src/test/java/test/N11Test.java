package test;

import org.testng.annotations.Test;
import pages.MainPage;
import logger.Log;
import pages.N11Page;


public class N11Test {

    MainPage mainPage = new MainPage();
    N11Page n11= new N11Page();
    Log log = new Log();

    @Test(description = "N11 UI Testi")
    public void N11SeleniumTest() {

        log.info("N11 UI Testi basladi");
//        Senaryo Başlangıcı ve Sayfa Erişimi:
//        Senaryo "n11.com" anasayfasında başlar.
//        Tarayıcıyı aç ve "https://www.n11.com/" adresine git.
        mainPage.setUp();
//        Üye Olma Sayfasına Geçiş:
//        Anasayfada "Üye Ol" veya "Giriş Yap" butonuna tıkla.
//        "Üye Ol" seçeneğine tıkla, yeni üye kaydı için sayfaya geçiş yap.
        n11.uyeOlButonu.click();
//        Kişisel Bilgilerin Girilmesi:
//        Ad ve soyad bilgilerini ilgili alanlara gir.
//                E-posta adresini ilgili alana gir.
//        Şifreyi belirle ve şifre tekrarını ilgili alanlara gir.
//        Üyelik Bilgilerinin Tamamlanması:
//        Cinsiyet seçeneğini uygun şekilde işaretle.
//                Doğum tarihini ilgili alana gir.
//        Telefon numarasını ilgili alana gir.
//                Üyelik Sözleşmesi ve İzinler:
//        Üyelik sözleşmesini ve gizlilik politikasını oku (isteğe bağlı).
//        Gerekliyse iletişim ve pazarlama izinlerini işaretle veya işaretsiz bırak.
//                Üyelik İşleminin Onaylanması:
//        "Üye Ol" veya "Kaydol" gibi bir onay butonuna tıkla.
//                Üyelik Onayı ve Giriş Yapma:
//        Üyelik işlemi başarılıysa, "Üyelik tamamlandı" mesajını doğrula.
//        E-posta adresine gönderilen doğrulama bağlantısını kontrol et (isteğe bağlı).
//        Giriş Yapma (Opsiyonel):
//        Üyelik işlemi tamamlandıktan sonra "Giriş Yap" butonuna tıkla.
//        Kullanıcı adı (e-posta) ve şifre ile oturum aç.
//                Çıkış ve Temizlik (Opsiyonel):
//        Senaryo tamamlandıktan sonra oturumu kapat veya sayfayı kapat.


    }


}