# Laboratorio Calificado 03 - Consumo de API REST con Android y Kotlin

## Descripción del Proyecto

Este proyecto de Android Studio, desarrollado en Kotlin, forma parte del Laboratorio Calificado 03. Su objetivo principal es demostrar la capacidad de consumir un servicio web (API REST) y presentar los datos obtenidos en una interfaz de usuario dinámica y responsiva utilizando `RecyclerView` y `ViewBinding`. La aplicación muestra una lista de profesores con su nombre, apellido y fotografía, permitiendo interacciones como llamadas telefónicas y envío de correos electrónicos mediante gestos de clic.

## Características Implementadas

* **Consumo de API REST:** Se conecta al endpoint `https://private-effe28-tecsup1.apiary-mock.com/list/teacher-b` para obtener datos de profesores.
* **Listado Dinámico:** Utiliza `RecyclerView` para mostrar eficientemente la lista de profesores.
* **ViewBinding:** Implementado para una interacción segura y eficiente con los elementos de la interfaz de usuario.
* **Clase de Datos (`Teacher`):** Estructura de datos para modelar la respuesta JSON del API.
* **Adaptador Personalizado (`TeacherAdapter`):** Maneja la lógica de presentación de cada elemento de la lista.
* **Carga de Imágenes:** Integración de la librería Glide para cargar y mostrar las fotografías de los profesores desde URLs.
* **Interacciones del Usuario:**
    * **Click Simple:** Inicia una llamada telefónica al número del profesor (utiliza `ACTION_DIAL`).
    * **Click Largo:** Abre la aplicación de correo para enviar un email al profesor (utiliza `ACTION_SENDTO`).
* **Modularización del Código:** Estructura de proyecto limpia con separación de responsabilidades (API Service, Retrofit Client, Adapter, Activity).
* **Externalización de Cadenas y Dimensiones:** Uso de `strings.xml` y `dimens.xml` para una buena práctica de internacionalización y diseño.

## Requisitos y Configuración

* **Android Studio:** Versión Artic Fox 2021.3.1 o superior (recomendado).
* **Lenguaje:** Kotlin.
* **Minimum SDK:** API 24 (Android 7.0 Nougat).
* **Gradle:** Configuración de dependencias para Retrofit, GSON, Glide y ViewBinding.
    (Asegúrate de tener las siguientes dependencias en tu `build.gradle` del módulo `app`):

    ```gradle
    // Retrofit & GSON
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'

    // Glide (para carga de imágenes)
    implementation 'com.github.bumptech.glide:glide:4.16.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.16.0'

    // ViewBinding (habilitar en buildFeatures)
    buildFeatures {
        viewBinding true
    }
    ```

* **Permisos:** Asegúrate de que los siguientes permisos estén declarados en `AndroidManifest.xml`:

    ```xml
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.CALL_PHONE" />
    ```

## Cómo Ejecutar el Proyecto

1.  **Clonar el Repositorio:**
    ```bash
    git clone [https://github.com/Anjelisahori/Laboratoriocalificado-03PM.git]
    ```
2.  **Abrir en Android Studio:**
    * Abre Android Studio y selecciona `File > Open`.
    * Navega a la carpeta clonada `LaboratorioCalificado03` y haz clic en `OK`.
3.  **Sincronizar Gradle:**
    * Android Studio debería sincronizar automáticamente los archivos Gradle. Si no lo hace, haz clic en `File > Sync Project with Gradle Files` o en el icono de "Sync Now" en la barra de herramientas.
4.  **Crear Vector Asset `ic_person_placeholder`:**
    * En la vista "Project" (no "Android"), navega a `app/src/main/res/drawable`.
    * Haz clic derecho en la carpeta `drawable`, selecciona `New > Vector Asset`.
    * Elige "Clip Art", busca un ícono de persona y nómbralo `ic_person_placeholder`.
5.  **Ejecutar en un Emulador o Dispositivo Físico:**
    * Selecciona un emulador configurado o conecta un dispositivo Android con la depuración USB habilitada.
    * Haz clic en el botón `Run 'app'` (el icono de triángulo verde) en la barra de herramientas de Android Studio.

## Observaciones

* [Aquí es donde debes añadir tus 5 observaciones mínimas. Por ejemplo:]
    1.  La librería Retrofit facilitó enormemente la conexión con el API REST y la deserialización de JSON a objetos Kotlin.
    2.  El uso de `ViewBinding` simplificó la interacción con los elementos de la UI, eliminando la necesidad de `findViewById` y reduciendo posibles errores de tipo.
    3.  Al implementar `ACTION_DIAL` en lugar de `ACTION_CALL` para la función de llamada, se prioriza la seguridad y la experiencia del usuario, ya que el usuario debe confirmar la llamada. Para `ACTION_CALL`, sería necesario gestionar permisos en tiempo de ejecución.
    4.  Fue crucial asegurar la correcta serialización de los nombres de campo con `@SerializedName` en la `data class Teacher` debido a las diferencias de formato entre el JSON (snake_case) y Kotlin (camelCase).
    5.  La gestión del placeholder de imagen con Glide (`.placeholder()` y `.error()`) es fundamental para proporcionar una buena experiencia visual, especialmente mientras las imágenes se cargan o si hay errores en la URL de la imagen.

## Conclusiones

* [Aquí es donde debes añadir tus 5 conclusiones mínimas. Por ejemplo:]
    1.  El laboratorio proporcionó una excelente oportunidad para aplicar conceptos clave de consumo de servicios web en Android, consolidando el uso de Retrofit y GSON como herramientas esenciales.
    2.  La arquitectura modular propuesta (separación de la lógica de red, datos y UI) demostró ser muy beneficiosa para la legibilidad, mantenimiento y escalabilidad del código.
    3.  El `RecyclerView` es una herramienta potente y flexible para mostrar listas de datos, y su combinación con un `Adapter` y `ViewBinding` permite crear interfaces de usuario fluidas y eficientes.
    4.  A pesar de pequeños desafíos iniciales con la configuración del `drawable` y la serialización de datos, la depuración y la revisión de la documentación oficial de Android y las librerías fueron clave para superar los obstáculos.
    5.  Este ejercicio reforzó la importancia de una buena gestión de errores en las llamadas a la red (`onResponse` y `onFailure`), lo que es crítico para informar adecuadamente al usuario sobre el estado de la aplicación.

---
