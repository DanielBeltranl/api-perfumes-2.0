@echo off
chcp 65001 >nul

echo.
echo ╔═══════════════════════════════════════════════════════════════════════════╗
echo ║                           🧪 EJECUTANDO PRUEBAS                           ║
echo ╚═══════════════════════════════════════════════════════════════════════════╝
echo.

call mvnw.cmd test

echo.
if %ERRORLEVEL% EQU 0 (
    echo ╔═══════════════════════════════════════════════════════════════════════════╗
    echo ║                               ✅ ÉXITO                                  ║
    echo ║                                                                           ║
    echo ║    Todas las pruebas pasaron correctamente                               ║
    echo ║    • ProductoControllerTest                                              ║
    echo ║    • ProductoServiceTest                                                 ║  
    echo ║    • ProductoApplicationTests                                            ║
    echo ║    • PerfulandiaApplicationTests                                         ║
    echo ║                                                                           ║
    echo ╚═══════════════════════════════════════════════════════════════════════════╝
) else (
    echo ╔═══════════════════════════════════════════════════════════════════════════╗
    echo ║                              ❌ ERROR                                   ║
    echo ║                                                                           ║
    echo ║    Algunas pruebas fallaron. Revisa la salida anterior.                 ║
    echo ║                                                                           ║
    echo ╚═══════════════════════════════════════════════════════════════════════════╝
)
echo.
pause
