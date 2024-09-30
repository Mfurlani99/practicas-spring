buscar https://start.spring.io

agregar dependencias spring boot DevTools , lombok ,spring web, thymeleaf, 
le das a generar 


crear controlador ControladorREST 
poner @restcontroller (importar las librerías si hay error)

al método que necesites que comience hay que agregarle @GetMapping("/")
public [tipo de dato string,int...] #nombre (){
==metodo==

return;
}


# Spring Boot Hola Mundo con Thymeleaf y MVC Imprimir/ Enviar Lista

`@GetMapping("/")`

`public String comienzo() {`

`return "indice"; // Sin la extensión .html`

`}`

en el caso que no dectete la vista probar instalar las dependencias en el pob
<dependency> <groupId>org.springframework.boot</groupId> <artifactId>spring-boot-starter-thymeleaf</artifactId> </dependency>



asi se agrega thymeleaf al html 
`<html xmlns:th="http://www.thymeleaf.org">`

`<head>`

`<meta charset="UTF-8">`

`<title>Insert title here</title>`

`</head>`

`<body>`

`<h1>hola mundo</h1>`

`<p th:text="'hola mundo'"></p>`

`</body>`

`</html>`

```
@GetMapping("/")

public String comienzo(Model model) {

String hola ="hola como tan";

model.addAttribute("mensaje",hola);

return "indice"; // Sin la extensión .html

}
```
de esta manera  agregamos un modelo para mandarle la información a la vista 

para que la vista resiva  el valor del modelo hay que usar  `<p th:text="${**mensaje**(este es el nombre del modelo)}"></p>`


## crear clase
crear clase pj persona 


```
@Data

public class persona {

private String nombre;

private String apellido;

private String telefono;

private String correo;

private String edad;
```

el @Data de lombok crea automáticamente los getter y setter de la clase 

para agregar los datos al modelo 

model.addAttribute("persona",persona);


para ver el atributo en la vista nombre: `<span th:text="${persona.nombre}"></span>`



### crear lista 

```
List<Persona> personas = new ArrayList<Persona>();

personas.add(personita);

personas.add(personita2);
```


se puede hacer mas simple de esta forma
`List personas = Arrays.asList(personita,personita2);`



La expresión `th:if="${personas != null and !personas.empty}"` es una condición en Thymeleaf que se utiliza para mostrar o no un bloque de contenido HTML basado en el estado de la variable `personas`.

### Desglose de la expresión:

1. **`${personas != null}`**:
    
    - **`personas`**: Es la variable que contiene una lista de objetos `Persona`. Esta variable fue pasada al modelo en tu controlador.
    - **`!= null`**: Esta parte verifica si la variable `personas` no es `null`. Es decir, verifica que la variable exista y haya sido inicializada.
2. **`and`**:
    
    - Este es un operador lógico que combina dos condiciones. Ambas deben ser verdaderas para que la expresión completa sea verdadera.
3. **`!personas.empty`**:
    
    - **`personas.empty`**: Esto verifica si la lista `personas` está vacía.
    - **`!`**: El operador de negación (exclamación) invierte el resultado. Entonces, `!personas.empty` significa "la lista `personas` no está vacía".

### Significado Completo:

La expresión `th:if="${personas != null and !personas.empty}"` significa que el contenido dentro de este bloque solo se mostrará si la variable `personas`:

1. No es nula (existe y fue inicializada).
2. No está vacía (contiene al menos un objeto `Persona`).

-------------------------------------------------------------------

La expresión `th:each="persona : ${personas}"` en Thymeleaf se utiliza para iterar sobre una colección de objetos y generar contenido repetitivo en tu vista HTML.

### Desglose de la expresión:

1. **`th:each`**:
    
    - Es una expresión de Thymeleaf que permite iterar sobre una colección (como una lista, conjunto o mapa) en tu vista.
2. **`"persona : ${personas}"`**:
    
    - **`persona`**: Es una variable local que representa el elemento actual en cada iteración de la colección. Puedes usar este nombre para acceder a las propiedades de cada objeto en la colección dentro del bloque de iteración.
    - **`${personas}`**: Es la colección de objetos que estás iterando. En tu caso, se espera que sea una lista de objetos `Persona` que has pasado al modelo.

### Significado Completo:

Esta expresión indica que Thymeleaf debe iterar sobre la colección `personas`. En cada iteración, asignará el objeto actual de la colección a la variable `persona`, la cual podrás usar dentro del bloque para mostrar datos o realizar otras operaciones.


```
@Data
@Entity
@Table(name = "individuo")
public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_individuo;

    private String nombre;
    private String apellido;
    private int edad;
    private String telefono;
    private String correo;
}

```

### Explicación

1. **Anotación `@Data`:**
    
    - Esta anotación proviene de la biblioteca **Lombok**.
    - Automáticamente genera código repetitivo como los métodos `getters`, `setters`, `toString()`, `equals()` y `hashCode()` para todos los campos de la clase.
    - También genera un **constructor** por defecto y uno que recibe todos los campos no `final`.
2. **Anotación `@Entity`:**
    
    - Marca la clase como una **entidad** JPA (Java Persistence API).
    - Esto significa que esta clase se mapeará a una tabla en la base de datos, permitiendo que los objetos de esta clase se puedan persistir (guardar) y recuperar desde la base de datos.
3. **Anotación `@Table(name = "individuo"):**
    
    - Especifica el nombre de la tabla en la base de datos a la que se mapeará esta entidad.
    - En este caso, la tabla se llama `individuo`, a pesar de que la clase se llama `Persona`.
    - Esto se hace porque el nombre de la tabla fue cambiado a "individuo" en tu proyecto.
4. **Implementación de la interfaz `Serializable`:**
    
    - Hace que la clase `Persona` implemente la interfaz `Serializable`.
    - Esto permite que los objetos de la clase se puedan convertir en una secuencia de bytes, lo cual es útil para almacenarlos o transferirlos.
    - Es una buena práctica cuando las entidades se usan en aplicaciones distribuidas.
5. **Campo `serialVersionUID`:**
    
    - Este campo se utiliza para asegurar la **compatibilidad** entre diferentes versiones de la clase durante la deserialización (cuando un objeto se reconstruye a partir de su secuencia de bytes).
    - Su valor es un número único, que en este caso es `1L`.
6. **Anotación `@Id`:**
    
    - Marca el campo `id_individuo` como la **clave primaria** de la entidad.
    - Este campo es único para cada registro en la tabla `individuo`.
7. **Anotación `@GeneratedValue(strategy = GenerationType.IDENTITY)`:**
    
    - Especifica que el valor de `id_individuo` se generará automáticamente en la base de datos.
    - La estrategia `GenerationType.IDENTITY` indica que la base de datos generará un valor único y secuencial para este campo cada vez que se inserte un nuevo registro.
8. **Otros Campos (`nombre`, `apellido`, `edad`, `telefono`, `correo`):**
    
    - Estos son los atributos de la clase `Persona`.
    - Se mapearán directamente a columnas en la tabla `individuo`.
    - Sus tipos corresponden a los tipos de datos que se almacenarán en la base de datos.

### Resumen:

Esta clase representa una entidad en una base de datos relacional, específicamente en una tabla llamada `individuo`. Utiliza las anotaciones de JPA para mapearse a la tabla y Lombok para reducir el código repetitivo. La clase es serializable, lo que la hace adecuada para persistir o transferir objetos en aplicaciones distribuidas.


### Modelo (Model):

- **Qué es:** El modelo es una representación de los datos que maneja la aplicación. En Java, normalmente se representa mediante clases que definen los atributos y el comportamiento de esos datos.
- **Ejemplo:** En tu aplicación, la clase `Persona` es un modelo. Tiene atributos como `nombre`, `apellido`, `edad`, `telefono`, y `correo`.
- **Función:** Sirve para almacenar y manipular la información que se guardará en la base de datos. También se utiliza para transferir datos entre las capas de la aplicación.

### DAO (Data Access Object):

- **Qué es:** El DAO es un componente que se encarga de la comunicación con la base de datos. Permite realizar operaciones como crear, leer, actualizar o borrar registros en la base de datos.
- **Ejemplo:** En tu aplicación, `PersonaDAO` es el DAO para la entidad `Persona`. Usa métodos como `findAll()` para obtener todas las personas de la base de datos.
- **Función:** Facilita el acceso a los datos de la base de datos de manera organizada, abstractando los detalles de la base de datos para que no tengas que escribir SQL directamente.

### Controlador (Controller):

- **Qué es:** El controlador es la capa que maneja las solicitudes HTTP (como acceder a una página web). Recibe las peticiones del usuario, interactúa con el modelo a través del DAO y luego devuelve una respuesta, como una página web o un dato.
- **Ejemplo:** En tu aplicación, `controladormvc` es el controlador. Maneja las solicitudes a la ruta `/` y usa `PersonaDAO` para obtener la lista de personas y pasarla a la vista `indice.html`.
- **Función:** Actúa como intermediario entre el usuario y el modelo. Se asegura de que el usuario vea la información correcta y pueda interactuar con la aplicación de manera adecuada.

---

### Resumen Visual:

- **Modelo (`Persona`):** Datos de la aplicación (nombre, apellido, etc.).
- **DAO (`PersonaDAO`):** Acceso a la base de datos (guardar, leer datos).
- **Controlador (`controladormvc`):** Manejo de las solicitudes del usuario (mostrar la lista de personas).


```
package com.example.demo.DAOS;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.utilidades.Persona;

@Repository
public interface PersonaDAO extends CrudRepository<Persona,Long> {

  

}
```
### Explicación

1. **Qué es:**
    
    - `PersonaDAO` es una **interfaz** que actúa como un **Data Access Object (DAO)** para la entidad `Persona`.
    - Se encarga de las operaciones básicas de persistencia, como guardar, buscar, actualizar y borrar objetos `Persona` en la base de datos.
2. **Herencia de `CrudRepository`:**
    
    - `PersonaDAO` extiende la interfaz `CrudRepository`, que es una interfaz genérica proporcionada por Spring Data.
    - `CrudRepository<Persona, Long>` significa que maneja operaciones CRUD (Create, Read, Update, Delete) para la entidad `Persona`, donde el tipo de su clave primaria es `Long`.
3. **Métodos Heredados:**
    
    - Al heredar de `CrudRepository`, `PersonaDAO` ya tiene acceso a métodos como:
        - `save(Persona persona)`: Guarda o actualiza una persona en la base de datos.
        - `findById(Long id)`: Busca una persona por su ID.
        - `findAll()`: Obtiene todas las personas de la base de datos.
        - `deleteById(Long id)`: Borra una persona usando su ID.
        - Y otros métodos CRUD.
4. **Anotación `@Repository`:**
    
    - Esta anotación indica que `PersonaDAO` es un **componente de acceso a datos** (un repositorio) en Spring.
    - Spring automáticamente detecta y configura este DAO para que puedas inyectarlo en otras partes de tu aplicación, como en un controlador.

### Resumen:

- **`PersonaDAO`** es un DAO que usa `CrudRepository` para proporcionar métodos CRUD automáticos para la entidad `Persona`.
- **No necesita implementación manual** porque Spring Data se encarga de generar el código necesario basado en la interfaz.
- **Se usa en combinación con otras capas** (como el controlador) para manejar la lógica de persistencia de datos en la aplicación.


# Método `mostrarFormulario`
```
@GetMapping("/formulario")
public String mostrarFormulario(Model model) {
    model.addAttribute("persona", new Persona());
    return "formulario";
}
```

- **Propósito:** Este método se encarga de mostrar el formulario para agregar una nueva persona.
    
- **Anotación `@GetMapping("/formulario")`:**
    
    - Indica que este método se ejecuta cuando se hace una solicitud HTTP GET a la URL `/formulario`.
    - Esta ruta generalmente se activa cuando un usuario navega a una página que muestra el formulario.
- **Parámetro `Model model`:**
    
    - Spring proporciona el objeto `Model` para pasar datos desde el controlador hacia la vista.
    - En este caso, se usa para pasar un objeto `Persona` a la vista.
- **`model.addAttribute("persona", new Persona())`:**
    
    - Se añade un objeto `Persona` vacío al modelo con la clave `"persona"`.
    - Esto permite que el formulario en la vista `formulario.html` se enlace con este objeto y pueda recibir los datos que el usuario ingrese.
- **`return "formulario"`:**
    
    - Especifica el nombre de la vista (archivo HTML) que se debe mostrar. En este caso, se espera que exista un archivo `formulario.html` en el directorio de vistas.
    - Este archivo mostrará el formulario para ingresar los datos de una nueva persona.


# Método `agregarPersona`

```
@PostMapping("/agregarPersona")
public String agregarPersona(@ModelAttribute Persona persona) {
    // Guardar la persona en la base de datos
    personaDAO.save(persona);
    return "redirect:/";
}

```

- **ropósito:** Este método maneja la solicitud de guardar los datos de una nueva persona después de que el formulario es enviado.
    
- **Anotación `@PostMapping("/agregarPersona")`:**
    
    - Indica que este método se ejecuta cuando se hace una solicitud HTTP POST a la URL `/agregarPersona`.
    - Esta ruta se activa cuando el usuario envía el formulario de la vista `formulario.html`.
- **Parámetro `@ModelAttribute Persona persona`:**
    
    - El objeto `Persona` es automáticamente creado y poblado con los datos enviados en el formulario gracias a la anotación `@ModelAttribute`.
    - Spring vincula los campos del formulario con los atributos del objeto `Persona` basándose en los nombres de los campos.
- **`personaDAO.save(persona)`:**
    
    - Este código guarda el objeto `Persona` en la base de datos utilizando el DAO (`personaDAO`), que ya está configurado para manejar operaciones CRUD.
    - Si la inserción es exitosa, la nueva persona queda guardada en la base de datos.
- **`return "redirect:/"`:**
    
    - Después de guardar la persona, este código redirige al usuario a la página principal (`/`).
    - La redirección evita que el formulario se vuelva a enviar si el usuario recarga la página.

### Resumen:

- **`mostrarFormulario` (`@GetMapping`):** Muestra el formulario para que el usuario ingrese los datos de una nueva persona. Prepara un objeto `Persona` vacío para que la vista lo use.
    
- **`agregarPersona` (`@PostMapping`):** Recibe los datos del formulario, guarda la nueva persona en la base de datos, y luego redirige al usuario a la página principal.
    

Este flujo de trabajo es común en aplicaciones web que permiten a los usuarios ingresar y guardar nuevos datos a través de formularios.


# SERVICIOS

### Métodos de `PersonaService`

1. **`listaPersonas()`**:
    
    - **Descripción**: Este método devolverá una lista (`List`) de objetos de tipo `Persona`. Se utiliza para obtener todas las personas almacenadas.
    - **Tipo de retorno**: `List<Persona>`.
2. **`salvar(Persona persona)`**:
    
    - **Descripción**: Este método se utiliza para guardar una nueva persona o actualizar la información de una persona existente.
    - **Parámetros**: Recibe un objeto de tipo `Persona` que representa la persona que se va a guardar o actualizar.
    - **Tipo de retorno**: `void` (no devuelve nada).
3. **`borrar(Persona persona)`**:
    
    - **Descripción**: Este método se encarga de eliminar una persona específica del sistema.
    - **Parámetros**: Recibe un objeto de tipo `Persona` que representa la persona que se va a eliminar.
    - **Tipo de retorno**: `void` (no devuelve nada).
4. **`localizarPersona(Persona persona)`**:
    
    - **Descripción**: Este método buscará una persona específica en el sistema y devolverá el objeto `Persona` correspondiente si se encuentra.
    - **Parámetros**: Recibe un objeto de tipo `Persona` que sirve para identificar la persona que se busca.
    - **Tipo de retorno**: `Persona`.

### ¿Cómo se usa?

Una clase que implemente esta interfaz (`PersonaService`) deberá proporcionar la implementación concreta de estos métodos. Por ejemplo, la clase podría conectarse a una base de datos para realizar las operaciones de guardar, listar, borrar o buscar personas.

### Resumen

- **`PersonaService`** define un conjunto de operaciones que se pueden realizar sobre objetos `Persona`.
- **No implementa** los métodos, solo define qué métodos estarán disponibles.
- Una clase concreta se encargará de dar vida a estas operaciones, es decir, de escribir el código que realmente haga el trabajo de guardar, listar, borrar y buscar personas.

Este código define la implementación concreta de la interfaz `PersonaService` utilizando Spring Framework. La clase se llama `PersonaServicioIMP` y proporciona la funcionalidad real para los métodos definidos en la interfaz `PersonaService`. Vamos a desglosarlo:

### Anotaciones

- **`@Service`**: Esta anotación indica que la clase es un **servicio** en el contexto de Spring, lo que significa que es un componente de la lógica de negocio. Spring se encargará de gestionar esta clase como un bean, permitiendo que sea inyectada en otros componentes.
    
- **`@Autowired`**: Esta anotación indica que Spring debe **inyectar automáticamente** la dependencia `PersonaDAO` en la clase `PersonaServicioIMP`. Esto significa que no es necesario crear manualmente una instancia de `PersonaDAO`; Spring lo hará por ti.
    
- **`@Transactional`**: Esta anotación se usa para gestionar las **transacciones** de la base de datos. Garantiza que las operaciones dentro del método se ejecuten como una única transacción. Si algo falla, la transacción puede ser revertida para evitar inconsistencias.
    

### Métodos Implementados

1. **`listaPersonas()`**:
    
    - **Funcionalidad**: Este método recupera y devuelve una lista de todas las personas almacenadas en la base de datos.
    - **Implementación**: Usa el método `findAll()` de `PersonaDAO` para obtener todos los registros de la base de datos.
    - **Transaccionalidad**: Marcado con `@Transactional(readOnly = true)`, lo que significa que la transacción es solo de lectura, lo que puede mejorar el rendimiento.
2. **`salvar(Persona persona)`**:
    
    - **Funcionalidad**: Guarda o actualiza un objeto `Persona` en la base de datos.
    - **Implementación**: Usa el método `save()` de `PersonaDAO` para guardar la persona.
    - **Transaccionalidad**: La transacción es de lectura y escritura (completa).
3. **`borrar(Persona persona)`**:
    
    - **Funcionalidad**: Elimina un objeto `Persona` de la base de datos.
    - **Implementación**: Usa el método `delete()` de `PersonaDAO` para eliminar la persona.
    - **Transaccionalidad**: La transacción es completa, permitiendo modificaciones en la base de datos.
4. **`localizarPersona(Persona persona)`**:
    
    - **Funcionalidad**: Busca una persona en la base de datos por su ID y la devuelve si se encuentra.
    - **Implementación**: Usa el método `findById()` de `PersonaDAO` para buscar la persona por su ID.
    - **Transaccionalidad**: Marcado como `readOnly`, ya que solo consulta la base de datos sin realizar cambios.

### Resumen

- **`PersonaServicioIMP`** implementa los métodos definidos en `PersonaService` utilizando un objeto `PersonaDAO` para interactuar con la base de datos.
- **`@Transactional`** se utiliza para garantizar que las operaciones se realicen dentro de una transacción, protegiendo la integridad de los datos.
- Spring maneja la inyección de dependencias y la gestión del ciclo de vida del servicio a través de anotaciones como `@Service` y `@Autowired`.

La anotación `@Transactional(readOnly = true)` se utiliza en métodos donde solo se necesita realizar operaciones de **lectura** en la base de datos. Aquí te explico por qué es útil y cuándo debería usarse:

### Razones para Usar `@Transactional(readOnly = true)`

1. **Optimización del Rendimiento**:
    
    - Al marcar una transacción como **solo lectura**, se le dice al sistema de gestión de la base de datos (DBMS) que no es necesario mantener ciertas características de las transacciones, como bloqueos de escritura. Esto puede mejorar el rendimiento, especialmente en bases de datos que realizan optimizaciones específicas para transacciones de solo lectura.
2. **Evitar Operaciones de Escritura**:
    
    - Al marcar un método con `readOnly = true`, se evita accidentalmente modificar datos dentro de ese método. Cualquier intento de modificar la base de datos (por ejemplo, hacer un `INSERT` o `UPDATE`) dentro de un método de solo lectura generará una excepción en algunas implementaciones de JPA, garantizando que el método solo realice operaciones de lectura.
3. **Gestión de Recursos**:
    
    - Las transacciones de solo lectura pueden ser menos costosas en términos de recursos. El sistema de gestión de transacciones puede omitir ciertas tareas relacionadas con la gestión de transacciones (como el registro de cambios) cuando sabe que la transacción no realizará ninguna modificación.

### Cuándo Usarlo

- **Consultas**: Cuando tienes un método que solo necesita leer datos, como buscar un registro en la base de datos o listar registros, es ideal usar `@Transactional(readOnly = true)`.
- **Evitar Modificaciones**: Si estás implementando un método que no debería modificar los datos bajo ninguna circunstancia, esto proporciona una capa adicional de seguridad.


# CREAR PLANTILLAS con THYMELEAF