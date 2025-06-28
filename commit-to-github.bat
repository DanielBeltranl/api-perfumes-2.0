@echo off
echo ================================================
echo    PREPARANDO COMMIT A GITHUB
echo ================================================
echo.

REM Verificar si git está instalado
git --version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Git no está instalado en este sistema.
    echo.
    echo Opciones:
    echo 1. Instalar Git desde: https://git-scm.com/download/win
    echo 2. Usar GitHub Desktop desde: https://desktop.github.com/
    echo.
    echo Después de instalar Git, ejecuta este script nuevamente.
    pause
    exit /b 1
)

echo Git detectado. Continuando...
echo.

REM Inicializar repositorio git
echo Inicializando repositorio...
git init

REM Configurar usuario (cambiar por tus datos)
echo Configurando usuario Git...
git config user.name "SriverosGit"
git config user.email "tu-email@ejemplo.com"

REM Agregar remote
echo Configurando remote...
git remote add origin https://github.com/SriverosGit/Perfulandia.git

REM Agregar todos los archivos
echo Agregando archivos...
git add .

REM Hacer commit
echo Haciendo commit...
git commit -m "Actualizacion completa del proyecto Perfulandia con pruebas unitarias"

REM Push forzado para sobreescribir
echo Subiendo cambios (sobreescribiendo rama)...
git push -f origin main

echo.
echo ================================================
echo    COMMIT COMPLETADO EXITOSAMENTE
echo ================================================
echo.
echo Tu proyecto ha sido subido a:
echo https://github.com/SriverosGit/Perfulandia
echo.
pause
