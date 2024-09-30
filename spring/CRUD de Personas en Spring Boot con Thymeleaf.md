
1. Controlador (`controladormvc.java`)

1.1. Importaciones y Anotaciones
```
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.servicio.PersonaService;
import com.example.demo.utilidades.Persona;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

```
- **`@Controller`**: Marca la clase como un controlador de Spring MVC.
- **`@Slf4j`**: Proporciona un logger para registrar mensajes.

1.2. Inyección de Dependencias
```
@Autowired
private PersonaService personaservicio;

```
**`@Autowired`**: Inyecta una instancia del servicio `PersonaService` en el controlador.


1.3. Mostrar Lista de Personas

```
@GetMapping("/")
public String comienzo(Model model) {
    String hola = "Hola, ¿cómo están?";
    List<Persona> personas = personaservicio.listaPersonas();
    model.addAttribute("saludo", hola);
    model.addAttribute("personas", personas);
    return "indice";
}

```

- **`@GetMapping("/")`**: Maneja solicitudes GET para la ruta raíz (`/`).
- **`Model model`**: Utilizado para pasar datos a la vista.
- **`personaservicio.listaPersonas()`**: Obtiene la lista de personas.
- **`model.addAttribute("saludo", hola)`**: Añade un saludo al modelo.
- **`model.addAttribute("personas", personas)`**: Añade la lista de personas al modelo.
- **`return "indice"`**: Devuelve la vista `indice.html`.

1.4. Mostrar Formulario de Creación

```
@GetMapping("/anexar")
public String anexar(Model model) {
    model.addAttribute("persona", new Persona());
    return "anexar";
}

```

- **`@GetMapping("/anexar")`**: Maneja solicitudes GET para `/anexar`.
- **`model.addAttribute("persona", new Persona())`**: Inicializa un nuevo objeto `Persona`.
- **`return "anexar"`**: Devuelve la vista `anexar.html`.

1.5. Guardar Persona

```
@PostMapping("/salvar")
public String salvar(Persona persona) {
    personaservicio.salvar(persona);
    return "redirect:/";
}

```

- **`@PostMapping("/salvar")`**: Maneja solicitudes POST para `/salvar`.
- **`Persona persona`**: El objeto `Persona` se rellena con los datos del formulario.
- **`personaservicio.salvar(persona)`**: Guarda o actualiza la persona.
- **`return "redirect:/";`**: Redirige a la vista principal.

1.6. Cargar Datos de Persona para Edición

```
@GetMapping("/cambiar/{id_individuo}")
public String cambiar(@PathVariable("id_individuo") Long id_individuo, Model model) {
    Persona persona = personaservicio.localizarPersona(new Persona(id_individuo));
    model.addAttribute("persona", persona);
    return "anexar";
}

```

- **`@GetMapping("/cambiar/{id_individuo}")`**: Maneja solicitudes GET para `/cambiar/{id_individuo}`.
- **`@PathVariable("id_individuo") Long id_individuo`**: Extrae el `id_individuo` de la URL.
- **`personaservicio.localizarPersona(new Persona(id_individuo))`**: Busca la persona por ID.
- **`model.addAttribute("persona", persona)`**: Añade la persona al modelo.
- **`return "anexar"`**: Devuelve la vista para editar (`anexar.html`).

1.7. Eliminar Persona

```
@GetMapping("/eliminar/{id_individuo}")
public String eliminar(@PathVariable("id_individuo") Long id_individuo) {
    Persona persona = personaservicio.localizarPersona(new Persona(id_individuo));
    if (persona != null) {
        personaservicio.borrar(persona);
    }
    return "redirect:/";
}

```

- **`@GetMapping("/eliminar/{id_individuo}")`**: Maneja solicitudes GET para `/eliminar/{id_individuo}`.
- **`@PathVariable("id_individuo") Long id_individuo`**: Extrae el `id_individuo` de la URL.
- **`personaservicio.localizarPersona(new Persona(id_individuo))`**: Busca la persona por ID.
- **`personaservicio.borrar(persona)`**: Elimina la persona.
- **`return "redirect:/";`**: Redirige a la vista principal.


## **2. Vistas**

2.1. Vista de Lista de Personas (`indice.html`)

```
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Lista de Personas</title>
</head>
<body>

    <h2>Lista de Personas</h2>
    <a th:href="@{/anexar}">crear persona</a>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Edad</th>
            <th>Teléfono</th>
            <th>Correo</th>
            <th>Modificar</th>
            <th>Eliminar</th>
        </tr>
        <tr th:each="persona : ${personas}">
            <td th:text="${persona.id_individuo}">ID</td>
            <td th:text="${persona.nombre}">Nombre</td>
            <td th:text="${persona.apellido}">Apellido</td>
            <td th:text="${persona.edad}">Edad</td>
            <td th:text="${persona.telefono}">Teléfono</td>
            <td th:text="${persona.correo}">Correo</td>
            <td>
                <a th:href="@{/cambiar/{id}(id=${persona.id_individuo})}" th:text="'Cambiar'"></a>
            </td>
            <td>
                <a th:href="@{/eliminar/{id}(id=${persona.id_individuo})}" th:text="'Eliminar'"></a>
            </td>
        </tr>
    </table>
</body>
</html>

```

- **Encabezado**: Muestra los nombres de las columnas en la tabla.
- **Cuerpo de la Tabla**: Itera sobre la lista de personas y muestra sus atributos.
- **Enlaces**:
    - **Crear**: Redirige a la vista de formulario de creación.
    - **Modificar**: Redirige a la vista de formulario con los datos para edición.
    - **Eliminar**: Realiza una solicitud para eliminar la persona.

2.2. Vista para Crear o Editar Persona (`anexar.html`)

```
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta charset="UTF-8">
    <title>Crear usuario</title>
</head>

<body>
    <h1>Formulario</h1>
    <a th:href="@{/}">volver</a>
    <br />
    <form th:action="@{/salvar}" method="post" th:object="${persona}">
        <ul>
            <li>
                <label for="nombre">Nombre: </label>
                <input type="text" name="nombre" th:field="*{nombre}" />
            </li>
            <li>
                <label for="apellido">Apellido: </label>
                <input type="text" name="apellido" th:field="*{apellido}" />
            </li>
            <li>
                <label for="edad">Edad: </label>
                <input type="number" name="edad" th:field="*{edad}" />
            </li>
            <li>
                <label for="telefono">Teléfono: </label>
                <input type="text" name="telefono" th:field="*{telefono}" />
            </li>
            <li>
                <label for="correo">Correo: </label>
                <input type="text" name="correo" th:field="*{correo}" />
            </li>
        </ul>
        <input type="submit" name="salvar" value="Salvar">
    </form>
</body>

</html>

```

- **Formulario**: Permite ingresar o editar los datos de una persona.
- **Enlace Volver**: Redirige a la vista principal.
- **`th:object="${persona}"`**: Enlaza el formulario con el objeto `Persona`.
- **Campos del Formulario**: Se enlazan con las propiedades del objeto `Persona` usando `th:field`.


# 
