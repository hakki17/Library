# Library
##### 29/01/2025  

---

### ===== Integrantes =====  
- **Alejandro Prieto**  
- **María Paula Sánchez Macías**

---
## CREAR PROYECTO CON MAVEN
### Se crea el proyecto maven con estas indicaciones  
>Grupo: edu.eci.cvds   
Artefacto: Library   
Paquete: edu.eci.cvds.tdd   
archetypeArtifactId: maven-archetype-quickstart  

![ProyectoMaven creado](https://github.com/hakki17/Library/blob/main/img/1.%20proyMaven.png)

---  

## AGREGAR DEPENDENCIA JUNIT5  
#### Se modifican las respectivas etiquetas en el pom.xml, de tal manera que queda de la siguiente manera:

![Pom modificado](https://github.com/hakki17/Library/blob/main/img/1.%20proyMaven.png)

#### Se compila el proyecto para verificar que si corre perfectamente

![package success](https://github.com/hakki17/Library/blob/main/img/CLEAN%20PACKAGE.png)

---

## AGREGAR ESQUELETO DEL PROYECTO

#### Se crean los paquetes

![](https://github.com/hakki17/Library/blob/main/img/TREE.png)

#### Así se crean las carpetas

![](https://github.com/hakki17/Library/blob/main/img/CARPETAS.png)

#### De esta manera se puede ver la clase book

![](https://github.com/hakki17/Library/blob/main/img/CLASE%20BOOK.png)

---

## CREACIÓN DE LAS PRUEBAS

#### pruebas de ADDBOOK

![](https://github.com/hakki17/Library/blob/main/img/PRUEBAS%20ADDBOOK.png)

#### pruebas de LOANABOOK

![]()

#### pruebas de RETURNBOOK

![](https://github.com/hakki17/Library/blob/main/img/10pruebasReturnLoan.png)

![](https://github.com/hakki17/Library/blob/main/img/11pruebasReturnLoan.png)

![](https://github.com/hakki17/Library/blob/main/img/12pruebasReturnLoan.png)

![](https://github.com/hakki17/Library/blob/main/img/13pruebasReturnLoan.png)

#### Cómo aun no se tiene un código para cada una de las pruebas, todas las pruebas fallan

![](https://github.com/hakki17/Library/blob/main/img/14fallanPruebasReturnLoan.png)

![](https://github.com/hakki17/Library/blob/main/img/Fallar%20pruebas%20BookLoanIniciales.png)

## CREACIÓN DE LOS MÉTODOS

#### Método ReturnBook

![](https://github.com/hakki17/Library/blob/main/img/15MetodoReturnLoan.png)

![](https://github.com/hakki17/Library/blob/main/img/16metodoReturnLoan.png)

#### Método LoanABook

![]()

#### Método AddBook

![]()

#### Luego de poner las construcciones de los métodos SI pasan las pruebas

![](https://github.com/hakki17/Library/blob/main/img/17pasanPruebas.png)

---

## COBERTURA Y JACOCO

#### plugin agregado a Jacoco

![](https://github.com/hakki17/Library/blob/main/img/PLUGIN.png)

#### luego de poner mvn jacoco:report Se crea la carpeta target donde adentro en index se encuentran los porcentajes que necesitamos, a continuación se van a ver un ejemplo de los porcentajes antes y despues

##### dentro de la carpeta target

![](https://github.com/hakki17/Library/blob/main/img/GENERACION%20INDEX%20EN%20SITE.png)

##### reportes

![](https://github.com/hakki17/Library/blob/main/img/REPORTE%20html%20DE%20FALLAS%20ADDBOOK.png)

![](https://github.com/hakki17/Library/blob/main/img/LOABOOK%20REPORTE%20INICIAL.png)

![](https://github.com/hakki17/Library/blob/main/img/REPORTE%20ADDBOOK%20DESPUES%20DE%20PROGRMARA.png)

![](https://github.com/hakki17/Library/blob/main/img/LOANBOOK%20REPORTE%20FINAL.png)

#### Luego de terminar las pruebas se hace un merge con cada una de las tres ramas que creamos para que todo se guarde en el main. A continuación se ve un ejemplo de solución de problemas que trae el merge

![](https://github.com/hakki17/Library/blob/main/img/Manejo%20de%20conflictos%20al%20merge%20ADDBOOK.png)

![](https://github.com/hakki17/Library/blob/main/img/Manejo%20de%20conflictos%20al%20merge%20testAddbook.png)

#### guardamos todo con clean package

![](https://github.com/hakki17/Library/blob/main/img/CLEAN%20PACKAGE.png)

---

## SONARQUBE

#### Se descarga docker destkop con el comando "docker pull sonarqube"

![](https://github.com/hakki17/Library/blob/main/img/18Dockerpullsonarqube.png)

#### Se arranca el servicio de SonarQube con el siguiente comando "docker run -d --name sonarqube -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9000:9000 sonarqube:latest"

![](https://github.com/hakki17/Library/blob/main/img/19docker2.png)

#### Se prueba que si se activó y esta corriendo el sonarqube

![](https://github.com/hakki17/Library/blob/main/img/21sonarqube%20funciona.png)

#### Se entra a SonarQube y se cambia la contraseña

![](https://github.com/hakki17/Library/blob/main/img/22sonar.png)

![](https://github.com/hakki17/Library/blob/main/img/23sonar.png)

#### Se crea el token

![](https://github.com/hakki17/Library/blob/main/img/24sonarToken.png)

#### Se le agrega esta extension al plugin

![](https://github.com/hakki17/Library/blob/main/img/25pluginsonar.png)

#### Y esta a las properities

![](https://github.com/hakki17/Library/blob/main/img/26sonarprpeties.png)

#### Todo se compila y guarda con "mvn package"

![](https://github.com/hakki17/Library/blob/main/img/27mvnpackage.png)

![](https://github.com/hakki17/Library/blob/main/img/28package.png)

#### Se genera la integración con "sonar mvn verify sonar:sonar -D sonar.token=[TOKEN_GENERADO]"

![](https://github.com/hakki17/Library/blob/main/img/29.png)

![](https://github.com/hakki17/Library/blob/main/img/30.png)

#### Así queda el reporte en docker

![](https://github.com/hakki17/Library/blob/main/img/31.png)