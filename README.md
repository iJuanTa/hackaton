Este proyecto consiste en un sistema backend desarrollado en Java con Spring Boot, diseñado para la gestión eficiente de tickets de soporte técnico en entornos empresariales o institucionales. La API REST, accesible mediante el endpoint /api/tickets, permite a los usuarios autorizados realizar operaciones completas de creación, consulta, actualización y eliminación de tickets. Cada ticket incluye campos esenciales como categoría, descripción detallada, ubicación, dependencia y prioridad (Alta, Media o Baja), facilitando un seguimiento estructurado y organizado de las solicitudes de mantenimiento.
La aplicación fue desarrollada en IntelliJ IDEA y utiliza tecnologías modernas como Spring Data JPA para la persistencia de datos, Lombok para reducir código repetitivo y Spring Security con autenticación basada en sesiones (form-login) para garantizar la seguridad. El control de acceso se implementa mediante roles de usuario: Administrador (gestión total), Solicitante (creación y seguimiento de tickets), Oficina de Mantenimiento (asignación y supervisión) y Operario (ejecución y cierre). La base de datos se gestiona con PostgreSQL y pgAdmin 4, y no requiere consultas manuales, ya que las tablas, relaciones y datos iniciales (incluidos usuarios de prueba) se generan automáticamente al iniciar la aplicación mediante scripts de datos.
Este sistema está listo para ejecutarse localmente o en producción, ofreciendo una solución robusta, segura y fácil de mantener. Al iniciar el proyecto, se crea toda la estructura necesaria y se cargan credenciales de prueba para cada rol, permitiendo una evaluación inmediata. Ideal para organizaciones que buscan optimizar la atención de solicitudes técnicas con un flujo de trabajo claro, trazable y protegido por autenticación y autorización basada en roles.



-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
END POINTS
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
VET TICKETES
metodo get
http://localhost:8080/api/tickets/ver


CREAR TICKETES
metodo post
http://localhost:8080/api/tickets/crear
cuerpo:
{
    "categoria":"computador dañado",
    "descripcion":"se me cayo",
    "ubicacion":"la plata",
    "evidencia":"foto sin camisa mostrando el computador dañado",
    "dependencia":"oficina"
}


ASIGNAR OPERARIO
metodo path
http://localhost:8080/api/tickets/1/asignar-operario
cuerpo: 
{
    "operario": ""
}

ASIGNAR PRIORIDAD
metodo path
http://localhost:8080/api/tickets/1/prioridad
cuerpo:
{
    "prioridad": ""
}


-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
DATOS IMPORTANTES
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

USUARIOS

----SOLICITANTES
{
    "username":"Carlos Perez",
    "password": "1234"
}

{
    "username":"Juan Villalba",
    "password": "1234"
}

{
    "username":"Santiago Marquez",
    "password": "1234"
}




----validacion
{
    "username":"Maria Gomez",
    "password": "1234"
}




---Operario
{
    "username": "Lucia Torres",
    "password": "1234"
}

{
    "username": "Santiago Calderon",
    "password": "1234"
}





---Admin
{
    "username": "Admin SGML",
    "password": "1234"
}

{
    "username":"Juan Tabares"
    "password":"1234"
}
