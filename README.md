# EyojEgoSwitchCase

https://github.com/user-attachments/assets/8ad6709b-6911-425e-bfbb-f49d0cf2f060


Eyoj Ego Switch Case, altı adet switch içeren ve bu switchlerden birinin (Ego Switch) diğer switchlerin durumunu kontrol ettiği bir Android uygulamasıdır. Ego Switch açık olduğunda diğer switchler devre dışı bırakılır, kapalı olduğunda ise diğer switchler aktif hale gelir. Aktif olan her switch, alt gezinme çubuğuna bir menü öğesi ekler ve bu menü öğesine tıklandığında ilgili switch ile ilgili detay sayfasına yönlendirilir.

## Özellikler

- **Ego Switch**: Diğer switchlerin durumunu kontrol eder.
- **Switch Yönetimi**: Ego Switch kapalıyken diğer switchlerin açılmasına ve kapatılmasına olanak tanır.
- **Dinamik Menü**: Aktif switchlere göre alt gezinme çubuğunda menü öğeleri dinamik olarak eklenir.
- **Detay Sayfaları**: Her bir switch için ayrı detay sayfası bulunmaktadır.

## Kullanılan Teknolojiler

- **Kotlin**: Android uygulama geliştirme için ana programlama dili.
- **Android Jetpack**: Uygulama mimarisi, navigasyon ve lifecycle yönetimi için kullanıldı.
  - **ViewModel**: UI verilerini yönetmek için.
  - **Navigation Component**: Uygulama içi navigasyonu yönetmek için.
- **Dagger Hilt**: Bağımlılık enjeksiyonu için.
- **Kotlin Symbol Processing (KSP)**: Annotation processing ve meta-programlama görevleri için.
- **Glide**: Resim yükleme ve önbellekleme için.

## Proje Mimarisi

- **MVVM (Model-View-ViewModel)**: Uygulamanın temel mimarisi olarak MVVM kullanılmıştır. Bu yapı, UI bileşenlerini veri modellerinden ayırır ve test edilebilirliği artırır.
- **Navigation Component**: Uygulama içi gezinme, Android Jetpack'in Navigation Component'i ile yönetilmektedir.
- **Bağımlılık Enjeksiyonu**: Dagger Hilt kullanılarak, bağımlılıklar daha kolay yönetilir ve test edilebilirlik artırılır.

## Kurulum ve Çalıştırma

Bu projeyi yerel ortamınızda çalıştırmak için aşağıdaki adımları izleyin:

1. **Depoyu Klonlayın**:
    ```bash
    git clone https://github.com/EyoJneSinE/EyojEgoSwitchCase.git
    cd EyojEgoSwitchCase
    ```

2. **Bağımlılıkları Yükleyin**:
    Android Studio'da projeyi açın ve gerekli bağımlılıkların otomatik olarak yüklenmesini sağlayın.

3. **Çalıştırın**:
    Android Studio'dan projenizi çalıştırın.

## Katkıda Bulunma

Katkıda bulunmak isterseniz, lütfen bir issue açın veya bir pull request gönderin. Katkılarınızı görmekten memnuniyet duyarız!

## Lisans

Bu proje MIT Lisansı altında lisanslanmıştır. Daha fazla bilgi için [LICENSE](LICENSE) dosyasına bakın.
