#!/bin/bash

echo " "
echo "#########################################################################"
echo "##               EJECUCIÓN DEL PIPELINE DE EVALUACIÓN                  ##"
echo "##           (FORZANDO ENTORNO NODE PARA EJECUTAR JAVA)                ##"
echo "#########################################################################"
echo "##   FECHA DE DESPLIEGUE: $(date)                                 ##"
echo "#########################################################################"
echo " "

echo "==> [PASO 1/4] Instalando OpenJDK 17, ya que el buildpack de Node no lo tiene..."
apk add openjdk17 || apt-get update && apt-get install -y openjdk-17-jre openjdk-17-jdk

echo "==> [PASO 2/4] Asignando permisos a Maven Wrapper..."
chmod +x ./mvnw

echo "==> [PASO 3/4] Iniciando la construcción de Spring Boot (Maven)..."
./mvnw clean install -DskipTests

if [ $? -eq 0 ]; then
  echo " "
  echo "*************************************************************************"
  echo "*** [PASO 4/4] CONSTRUCCIÓN EXITOSA. ARTEFACTO .JAR GENERADO.  ***"
  echo "*************************************************************************"
  echo " "
else
  echo "!!! [ERROR CRÍTICO] FALLA EN LA CONSTRUCCIÓN. !!!"
  exit 1
fi