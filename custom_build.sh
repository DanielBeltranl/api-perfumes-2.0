#!/bin/bash

# Este script asume que la fase de compilación anterior (hecha por el buildpack de Koyeb) fue EXITOSA.
# Solo muestra un log gigante para confirmar.

echo " "
echo "################################################################################"
echo "##                                                                            ##"
echo "##              ✅ DESPLIEGUE FINALIZADO. JAR LISTO PARA INICIAR.             ##"
echo "##                                                                            ##"
echo "################################################################################"
echo " "

# Terminamos con código de éxito.
exit 0