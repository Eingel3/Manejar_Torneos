# Manejar_Torneos
Proyecto basado en java que se encarga de manejar torneos deportivos mediante una interfaz gráfica.


# Diagrama de casos de uso
<img width="6000" height="3375" alt="Diagrama casos de uso Torneo Deportivo (1)" src="https://github.com/user-attachments/assets/ec50f536-e01c-444c-b5ea-4ea0f209e098" />

Link al diagrama en canva:

https://canva.link/x6ek7qdzdzhf240


## Cómo ejecutar el proyecto

  ### Opción 1: Desde IntelliJ IDEA
    1. Clonar el repositorio
    2. Abrir la carpeta `Codigo` en IntelliJ
    3. Esperar a que Maven descargue las dependencias automáticamente
    4. Ejecutar `Main.java`
  ### Opción 2: Desde terminal
    ```bash
    cd Codigo
    mvn compile
    mvn exec:java
  
  Librerías
  Gson	2.10.1	Serialización/deserialización JSON
  exec-maven-plugin	3.1.0	Ejecutar el proyecto desde Maven

##Instrucciones de uso:

Usted puede tomar el rol de dos tipos de usuarios distintos: Organizador o usuario común.

El organizador puede:
    Crear nuevos torneos, designar los participantes, indicar quién ha ganado cada partido

 Un usuario común y el organizador pueden:
  Acceder a una lista con todos los torneos que existen, acceder a los detalles de dicho torneo

##Patrones de diseño utilizados 

### Strategy
Utilizado en las clases que implementan la interfaz formato: LigaSimple, EliminacionDirecta, DobleEliminacion
Permite cambiar algoritmos de generación de partidos en tiempo de ejecución
Cada formato encapsula su propia lógica
Se pueden crear nuevos formatos fácilmente y sin modificar el código existente (principio abierto-cerrado u open-closed).

### Factory Method Pattern
Usado en las siguientes clases:

BotonFactory (interface)
└── BotonSimple

PanelFactory (interface)
├── PanelLateral
├── PanelTarjeta
└── SubPanel

Este patron es utilizado para:
- Evitar la repeticion de codigo a la hora de crear nuevos paneles y botones
- Facilitar el cambio de estilos de los componentes gráficos (Cambiar sus colores, tamaños, la presencia de un borde, etc)
- Centraliza la creación de componentes gráficos

### 3. MVC Pattern  (Implementacion parcial)
Estructura:

model/      -> Torneo, Partido, Participante, etc.
gui/        -> VentanaPrincipal, PanelCrearTorneo, etc.
controller/ -> TorneoController, DeporteController, etc.

este patrón se utiliza para dividir las responsabilidades del código en 3:

1) Parte lógica: Que no conoce de la parte gráfica ni de los controllers
2) Parte gráfica/del gui: Que no conoce de la parte lógica
3) Parte Controller: Quien actua de puente entre la parte gráfica y lógica

Este patrón de diseño es muy útil, ya que, asegura una correcta encapsulación que:
  - Permite editar una de las partes sin necesidad de editar todo el código
  - Asegura que a futuro sea más fácil modificar el código

Sin embargo, en nuestro actual proyecto existe una mezclade responsabilidades, por ejemplo, dentro de PanelCrearTorneo:
        btnCrear.addActionListener(e -> {
            if (getNombre().isEmpty() || getDeporte().isEmpty()) {
                mostrarMensaje("Llenar campos de Nombre y Deporte");
                return;
            }

Existen estas líneas de código (líneas 58-62) que realizan acciones pertenecientes a la parte del controller, por ello, el uso de este patrón no está completamente implementado actualmente, y, como grupo, para poder mejorar la calidad del código deberíamos de aplicar completamente este patrón de diseño, sin embargo, debido a la falta de tiempo hemos decidido priorizar otros objetivos, que se detallarán más adelante.


### 4. Observer Pattern

Este patrón es utilizado a lo largo del proyecto, principalmente dentro de la parte de controller
Este patrón nos permite crear


### 5. Singleton Pattern

Es utilizado en PanelInformacion
Este patrón garantiza la existencia de una única instancia de PanelInformacion, y, por consiguiente, facilita la modificación de sus atributos.
Actualmente PanelInformacion se utiliza para determinar los tamaños de las pestañas y de la interfaz gráfica en general.

##Decisiones claves tomadas durante el proyecto.

Durante el proyecto hemos enfrentado varios desafíos, entre ellos:

 - Deseabamos implementar una funcio¿ón de "Persistencia", que permitiría guardar los torneos creados y los cambios hechos al usar la aplicación, sin embargo, debido a desafíos de tiempo y luego de hablar con el ayudante designado a ayudarnos (Bryan Eliseo Aguirre Fuentes) decidimos centrarnos en las funcionalidades gráficas y de creación de torneos y participantes en vez de esa función.

 - Como se mencionó anteriormente, no se implementó por completo el patrón de diseño MVC, esta decisión fue tomada ya que deseabamos terminar con todas las funcionalidades escenciales primero, y, el mezclar en ciertas partes la parte del controller y la parte gráfica nos permitía avanzar más rápidamente, por ello, decidimos priorizar el tener un proyecto más completo y funcional por sobre mantener la pureza del patrón de diseño MVC.

 - Decidimos crear varias clases distintas para un correcto encapsulamiento y una correcta división de responsabilidades, sin embargo, ello también provocó que la creación del diagrama UML sea más desafiante, por ello, hemos decidido dividir nuestro diagrama UML en varias partes, priorizando una mejor comprensión de este.

 - Para esta aplicación decidimos que existirían dos usuarios distintos, un usuario regular y un usuario tipo organizador, sin embargo, dentro del código no hemos implementado formas de separar estos dos tipos de usuarios debido a que ello no representaba una función principal y, como lo hablamos con Bryan Eliseo Aguirre Fuentes, aquella función representaba una complejidad mayor y decidimos centrarnos en otras funciones.
