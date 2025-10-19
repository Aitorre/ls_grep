# Proceso LS y GREP

Al ejecutar este código simula como si se estuviera ejecutando en la terminal de Linux el comando:

```bash
ls | grep a 
```

Con esto enviamos lo que reciba el ls al grep a para que filtre aquellos documentos que contengan la letra a

```java
BufferedReader salidaProcesoLs = new BufferedReader(new InputStreamReader(procesoLs.getInputStream()));
OutputStream entradaProcesoGrep = procesoGrep.getOutputStream();

while ((linea = salidaProcesoLs.readLine()) != null) {
    entradaProcesoGrep.write((linea + SALTO_LINEA).getBytes());
}
```

Luego podemos ver todos los documentos filtrados por el grep a usando el siguiente código

```java
BufferedReader salidaFinal = new BufferedReader(new InputStreamReader(procesoGrep.getInputStream()));

while ((linea = salidaFinal.readLine()) != null) {
    System.out.println(linea);
}
```

Enlace de GitHub: <https://github.com/Aitorre/ls_grep.git>
