@echo off
echo ==========================================
echo     HACIENDO COMMIT A GITHUB
echo ==========================================
echo.

REM Buscar Git en ubicaciones comunes
set "GIT_PATH="
if exist "C:\Program Files\Git\bin\git.exe" set "GIT_PATH=C:\Program Files\Git\bin\git.exe"
if exist "C:\Program Files (x86)\Git\bin\git.exe" set "GIT_PATH=C:\Program Files (x86)\Git\bin\git.exe"
if exist "C:\Users\%USERNAME%\AppData\Local\Programs\Git\bin\git.exe" set "GIT_PATH=C:\Users\%USERNAME%\AppData\Local\Programs\Git\bin\git.exe"

REM Verificar si Git está en PATH
git --version >nul 2>&1
if %errorlevel% equ 0 (
    set "GIT_PATH=git"
)

REM Si no encuentra Git
if "%GIT_PATH%"=="" (
    echo ERROR: No se pudo encontrar Git instalado.
    echo.
    echo Opciones:
    echo 1. Usar VS Code: Abre VS Code en esta carpeta y usa Source Control
    echo 2. Instalar Git: https://git-scm.com/download/win
    echo 3. Usar GitHub Desktop: https://desktop.github.com/
    echo.
    pause
    exit /b 1
)

echo Git encontrado en: %GIT_PATH%
echo.

REM Verificar si es un repositorio git
if not exist ".git" (
    echo Este directorio no es un repositorio Git.
    echo Inicializando repositorio...
    "%GIT_PATH%" init
    "%GIT_PATH%" remote add origin https://github.com/SriverosGit/Perfulandia.git
)

REM Verificar estado
echo Verificando estado del repositorio...
"%GIT_PATH%" status

REM Agregar archivos
echo.
echo Agregando archivos modificados...
"%GIT_PATH%" add .

REM Hacer commit
echo.
set /p commit_msg="Ingresa el mensaje del commit (o presiona Enter para usar mensaje por defecto): "
if "%commit_msg%"=="" set commit_msg=Actualizar proyecto Perfulandia con pruebas unitarias

echo Haciendo commit...
"%GIT_PATH%" commit -m "%commit_msg%"

REM Push
echo.
echo Subiendo cambios a GitHub...
"%GIT_PATH%" push origin main

if %errorlevel% equ 0 (
    echo.
    echo ==========================================
    echo     COMMIT EXITOSO
    echo ==========================================
    echo.
    echo Cambios subidos a: https://github.com/SriverosGit/Perfulandia
) else (
    echo.
    echo Error al hacer push. Puede necesitar autenticacion.
    echo Verifica tu usuario y token de GitHub.
)

echo.
pause
