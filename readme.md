# Hazelcast

Este proyecto es una demo para la presentación de la asignatura Sistemas de Almacenamiento del máster en Big Data y Analytics curso 2017/2017 de la U-TAD


## TOC

+ Arrancar cluster de Hazelcast de un nodo
+ Arrancar cluster de Hazelcast con 2 nodos
+ Escuchar cambios en estructuras de datos distribuidas de un cluster de dos nodos Hazelcast





## Arrancar cluster de Hazelcast

### Test

En el test se refleja lo sencillo que es arrancar una instancia de Hazelcast

``` bash
mvn -Dtest="org.miguelhalys.test.hazelcast.server.SimpleServerTest#shouldBootUp" test
```


### Ejecutar 

``` bash
mvn clean compile exec:java -Dexec.mainClass="com.miguelhalys.hazelcast.server.SimpleServer"
```

Se obtiene una salida como la siguiente:

```
[INFO] --- exec-maven-plugin:1.5.0:java (default-cli) @ hazelcast-demo ---
ene 31, 2018 9:08:49 PM com.hazelcast.config.XmlConfigLocator
INFO: Loading 'hazelcast-default.xml' from classpath.
ene 31, 2018 9:08:49 PM com.hazelcast.instance.DefaultAddressPicker
INFO: [LOCAL] [dev] [3.4.6] Prefer IPv4 stack is true.
ene 31, 2018 9:08:49 PM com.hazelcast.instance.DefaultAddressPicker
INFO: [LOCAL] [dev] [3.4.6] Picked Address[192.168.52.1]:5701, using socket ServerSocket[addr=/0:0:0:0:0:0:0:0,localport=5701], bind any local is true
ene 31, 2018 9:08:49 PM com.hazelcast.spi.OperationService
INFO: [192.168.52.1]:5701 [dev] [3.4.6] Backpressure is disabled
ene 31, 2018 9:08:49 PM com.hazelcast.spi.impl.BasicOperationScheduler
INFO: [192.168.52.1]:5701 [dev] [3.4.6] Starting with 2 generic operation threads and 4 partition operation threads.
ene 31, 2018 9:08:50 PM com.hazelcast.system
INFO: [192.168.52.1]:5701 [dev] [3.4.6] Hazelcast 3.4.6 (20150914 - 74c48e6) starting at Address[192.168.52.1]:5701
ene 31, 2018 9:08:50 PM com.hazelcast.system
INFO: [192.168.52.1]:5701 [dev] [3.4.6] Copyright (C) 2008-2014 Hazelcast.com
ene 31, 2018 9:08:50 PM com.hazelcast.instance.Node
INFO: [192.168.52.1]:5701 [dev] [3.4.6] Creating MulticastJoiner
ene 31, 2018 9:08:50 PM com.hazelcast.core.LifecycleService
INFO: [192.168.52.1]:5701 [dev] [3.4.6] Address[192.168.52.1]:5701 is STARTING
ene 31, 2018 9:08:52 PM com.hazelcast.cluster.impl.MulticastJoiner
INFO: [192.168.52.1]:5701 [dev] [3.4.6]


Members [1] {
        Member [192.168.52.1]:5701 this
}

ene 31, 2018 9:08:52 PM com.hazelcast.core.LifecycleService
INFO: [192.168.52.1]:5701 [dev] [3.4.6] Address[192.168.52.1]:5701 is STARTED
```

## Arrancar cluster de Hazelcast con dos nodos

### Test

En el test se refleja lo sencillo que es arrancar un cluster de dos nodos de Hazelcast

``` bash
mvn clean -Dtest=org.miguelhalys.test.hazelcast.server.SimpleServerTest#shouldBootUpTwoInstances test
```

### Ejecutar

Para poder levantar un cluster de dos nodos vamos a empaquetar la aplicación en un jar autoejecutable

``` bash
mvn clean package
```

abrir dos consolas y lanzar en cada consola, el siguiente comando desde el directorio target/

``` bash
java -jar com.miguelhalys.hazelcast.server.SimpleServer
```

y obtendremos una salida como la siguiente
```
...

Members [1] {
        Member [192.168.52.1]:5701 this
}
...
INFO: [192.168.52.1]:5701 [dev] [3.4.6] Accepting socket connection from /192.168.52.1:20757
ene 31, 2018 10:58:03 PM com.hazelcast.nio.tcp.TcpIpConnectionManager
INFO: [192.168.52.1]:5701 [dev] [3.4.6] Established socket connection between /192.168.52.1:5701 and /192.168.52.1:20757
ene 31, 2018 10:58:10 PM com.hazelcast.cluster.ClusterService
INFO: [192.168.52.1]:5701 [dev] [3.4.6]

Members [2] {
        Member [192.168.52.1]:5701 this
        Member [192.168.52.1]:5702
}
...
```

## Escuchar cambios en estructuras de datos distribuidas de un cluster de dos nodos Hazelcast

### Test

``` bash
mvn clean -Dtest=org.miguelhalys.test.hazelcast.events.SimpleListenerTest#listenToChanges test
```

### Ejecutar

TO-DO


