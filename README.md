# Hotels

Приложение для "бронирования" комнат в отелях. Оно разработано с использованием принципов Clean Architecture и организовано в многомодульную структуру на основе фич. 
В приложении используются следующие технологии и библиотеки: Retrofit, Dagger, Glide, Coroutines, LiveData и Navigation Component. Архитектурный паттерн, примененный в приложении - MVVM. 

---

## Структура

- app - модуль приложения, собирающий приложение в единое целое.
- core - модуль ядра, содержащий общие для нескольких модулей объекты и ресурсы.
- features - модуль фич, условно разделённый на экраны.
  - hotel - содержит информацию об отелях
  - room - содержит информацию о комнатах
  - booking - содержит информацию, необходимую для бронирования комнаты
---
## Зависимости

- Retrofit - библиотека для работы с сетью, используется для обмена данными между клиентом и сервером бронирования комнат.
- Dagger - фреймворк для внедрения зависимостей, обеспечивает управление зависимостями в приложении.
- Glide - библиотека для загрузки и отображения изображений из сети.
- Coroutines - библиотека для асинхронного программирования, используется для работы с сетью и выполнения асинхронных задач.
- LiveData - компонент архитектуры Android Jetpack, позволяет создавать отслеживаемые переменные для реактивного поведения в приложении.
- Navigation Component - библиотека для управления навигацией внутри приложения.
---
### Скриншоты и видео

<img src="https://raw.githubusercontent.com/OTende/Hotels/main/media/screenshots/1.png" title="" alt="" width="333"> <img src="https://raw.githubusercontent.com/OTende/Hotels/main/media/screenshots/2.png" title="" alt="" width="333">
<img src="https://raw.githubusercontent.com/OTende/Hotels/main/media/screenshots/3.png" title="" alt="" width="asd">


https://github.com/OTende/Hotels/assets/55776332/b4952f45-faac-4012-9968-392b8fb6e25b


https://github.com/OTende/Hotels/assets/55776332/d10f4693-2494-4677-87b4-924820fe524a


https://github.com/OTende/Hotels/assets/55776332/0d93163c-d89e-4380-aa1f-cb26b633864a

